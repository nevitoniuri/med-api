package med.voll.api.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.voll.api.exception.InvalidDataException;
import med.voll.api.exception.ResourceNotFoundException;
import med.voll.api.model.Consulta;
import med.voll.api.model.MotivoCancelamento;
import med.voll.api.model.StatusConsulta;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.service.ConsultaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository repository;

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

    public boolean existeConsultaComMedicoNoHorario(Long medicoId, LocalDateTime dataHora) {
        return repository.existsByMedicoIdAndDataHora(medicoId, dataHora);
    }

    //TODO: como refatorar?
    public void checkValid(Consulta consulta) {

        validateDataHora(consulta.getDataHora());

        if (!consulta.getPaciente().isAtivo()) {
            throw new InvalidDataException("Paciente inativo");
        }

        if (!consulta.getMedico().isAtivo()) {
            throw new InvalidDataException("Médico inativo");
        }

        if (repository.existsByPacienteAndDataHora(consulta.getPaciente(), consulta.getDataHora())) {
            throw new InvalidDataException("Paciente já possui consulta marcada para o dia");
        }
    }

    public void validateDataHora(LocalDateTime dataHora) {
        if (isLessThan30MinutesAntecedence(dataHora)) {
            throw new InvalidDataException("Consulta deve ser agendada com pelo menos 30 minutos de antecedência");
        }

        if (isMoreThan30DaysAntecedence(dataHora)) {
            throw new InvalidDataException("Consulta deve ser agendada com no máximo 30 dias de antecedência");
        }

        if (isHoraInvalid(dataHora)) {
            throw new InvalidDataException("Consulta deve ser agendada entre 7h e 19h");
        }

        if (isDomingo(dataHora)) {
            throw new InvalidDataException("Consulta não pode ser agendada para domingo");
        }
    }

    public boolean isLessThan30MinutesAntecedence(LocalDateTime dataHora) {
        return dataHora.isBefore(LocalDateTime.now().plusMinutes(30));
    }

    public boolean isMoreThan30DaysAntecedence(LocalDateTime dataHora) {
        return dataHora.isAfter(LocalDateTime.now().plusDays(30));
    }

    public boolean isHoraInvalid(LocalDateTime dataHora) {
        return dataHora.getHour() < 7 || dataHora.getHour() > 19;
    }

    public boolean isDomingo(LocalDateTime dataHora) {
        return dataHora.getDayOfWeek().equals(DayOfWeek.SUNDAY);
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
