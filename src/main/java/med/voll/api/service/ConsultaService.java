package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.exception.InvalidDataException;
import med.voll.api.model.Consulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository repository;

    public void create(Consulta consulta) {
        checkValid(consulta);
        repository.save(consulta);
    }

    public boolean existeConsultaComMedicoNoHorario(Long medicoId, LocalDateTime dataHora) {
        return repository.existsByMedicoIdAndDataHora(medicoId, dataHora);
    }

    public void checkValid(Consulta consulta) {

        if (!consulta.getPaciente().isAtivo()) {
            throw new InvalidDataException("Paciente inativo");
        }

        if (!consulta.getMedico().isAtivo()) {
            throw new InvalidDataException("Médico inativo");
        }

        if (isLessThan30MinutesAntecedence(consulta.getDataHora())) {
            throw new InvalidDataException("Consulta deve ser agendada com pelo menos 30 minutos de antecedência");
        }

        if (isMoreThan30DaysAntecedence(consulta.getDataHora())) {
            throw new InvalidDataException("Consulta deve ser agendada com no máximo 30 dias de antecedência");
        }

        if (isHoraInvalid(consulta.getDataHora())) {
            throw new InvalidDataException("Consulta deve ser agendada entre 7h e 19h");
        }

        if (isDomingo(consulta.getDataHora())) {
            throw new InvalidDataException("Consulta não pode ser agendada para domingo");
        }

        if (repository.existsByPacienteAndDataHora(consulta.getPaciente(), consulta.getDataHora())) {
            throw new InvalidDataException("Paciente já possui consulta marcada para o dia");
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

}
