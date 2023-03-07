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

    public void save(Consulta consulta) {
        checkValid(consulta);
        repository.save(consulta);
    }

    //TODO: como refatorar?
    private void checkValid(Consulta consulta) {

        consultaHorarioValidation.validateDataHora(consulta.getDataHora());

        if (!consulta.getPaciente().isAtivo()) {
            throw new InvalidDataException("Paciente inativo");
        }
        if (!consulta.getMedico().isAtivo()) {
            throw new InvalidDataException("Médico inativo");
        }
        if (existeConsultaNoDia(consulta.getPaciente(), consulta.getDataHora())) {
            throw new InvalidDataException("Paciente já possui consulta marcada para o dia");
        }
    }

    private boolean existeConsultaNoDia(Paciente paciente, LocalDateTime dataHora) {
        return repository.existsByPacienteIdAndDataHoraBetween(paciente.getId(),
                dataHora.withHour(INICIO_DIA).withMinute(0),
                dataHora.withHour(FIM_DIA).withMinute(59));
    }

    @Transactional
    public void reagendar(Consulta consulta, LocalDateTime dataHora) {
        consulta.setDataHora(dataHora);
        consulta.setStatus(StatusConsulta.REAGENDADA);
        save(consulta);
    }

    @Transactional
    public void cancelar(Consulta consulta, MotivoCancelamento motivo) {
        consulta.setMotivoCancelamento(motivo);
        consulta.setStatus(StatusConsulta.CANCELADA);
        repository.save(consulta);
    }

}
