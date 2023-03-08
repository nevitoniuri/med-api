package med.api.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.api.domain.enums.MotivoCancelamento;
import med.api.domain.enums.StatusConsulta;
import med.api.domain.model.Consulta;
import med.api.domain.model.Paciente;
import med.api.exception.InvalidDataException;
import med.api.exception.ResourceNotFoundException;
import med.api.repository.ConsultaRepository;
import med.api.service.ConsultaService;
import med.api.service.validation.ConsultaHorarioValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

import static med.api.common.Constantes.FIM_DIA;
import static med.api.common.Constantes.INICIO_DIA;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository repository;
    private final ConsultaHorarioValidation consultaHorarioValidation;

    public Consulta findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
    }

    public Page<Consulta> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    //TODO: Implementar a situação de PODER agendar uma consulta num horário que outra consulta com o mesmo médico já foi CANCELADA
    public void agendar(Consulta consulta) {
        checkValidAgendar(consulta);
        repository.save(consulta);
    }

    //TODO: como refatorar?
    private void checkValidAgendar(Consulta consulta) {

        consultaHorarioValidation.validateDataHoraAgendar(consulta.getDataHora());

        if (!consulta.getPaciente().isAtivo()) {
            throw new InvalidDataException("Paciente inativo");
        }
        if (!consulta.getMedico().isAtivo()) {
            throw new InvalidDataException("Médico inativo");
        }
        if (pacienteTemConsultaNoDia(consulta.getPaciente(), consulta.getDataHora())) {
            throw new InvalidDataException("Paciente já possui consulta marcada para o dia");
        }
    }

    private boolean pacienteTemConsultaNoDia(Paciente paciente, LocalDateTime dataHora) {
        return repository.existsByPacienteIdAndDataHoraBetween(paciente.getId(),
                dataHora.withHour(INICIO_DIA).withMinute(0),
                dataHora.withHour(FIM_DIA).withMinute(59));
    }

    @Transactional
    public void reagendar(Consulta consulta, LocalDateTime dataHora) {
        consulta.setDataHora(dataHora);
        consulta.setStatus(StatusConsulta.REAGENDADA);
        agendar(consulta);
    }

    @Transactional
    public void cancelar(Consulta consulta, MotivoCancelamento motivo) {
        checkValidCancelar(consulta);
        consulta.setMotivoCancelamento(motivo);
        consulta.setStatus(StatusConsulta.CANCELADA);
        repository.save(consulta);
    }

    private void checkValidCancelar(Consulta consulta) {
        if (consulta.getStatus() == StatusConsulta.CANCELADA) {
            throw new InvalidDataException("Consulta já está cancelada");
        }
        if (consulta.getStatus() == StatusConsulta.REALIZADA) {
            throw new InvalidDataException("Consulta já foi realizada");
        }
        if (Duration.between(LocalDateTime.now(), consulta.getDataHora()).toHours() < 24) {
            throw new InvalidDataException("Consulta não pode ser cancelada com menos de 24 horas de antecedência");
        }
    }

}
