package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.model.Consulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository repository;
    private final MedicoService medicoService;

    public void create(Consulta consulta) {


    }


    public void check(Consulta consulta) {

        if (!consulta.getPaciente().isAtivo()) {
            throw new IllegalArgumentException("Paciente inativo");
        }

        if (!consulta.getMedico().isAtivo()) {
            throw new IllegalArgumentException("Médico inativo");
        }

        if (consulta.getDataHora().isBefore(LocalDateTime.now().minusMinutes(30))) {
            throw new IllegalArgumentException("Data e hora inválidas");
        }

        if (consulta.getDataHora().isAfter(LocalDateTime.now().plusDays(30))) {
            throw new IllegalArgumentException("Data e hora inválidas");
        }

        if (consulta.getDataHora().getHour() < 7 || consulta.getDataHora().getHour() >= 19) {
            throw new IllegalArgumentException("Data e hora inválidas");
        }

        if (consulta.getDataHora().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new IllegalArgumentException("Data e hora inválidas");
        }

        if (repository.existsByPacienteAndDataHora(consulta.getPaciente(), consulta.getDataHora())) {
            throw new IllegalArgumentException("Paciente já possui consulta marcada para o dia");
        }


    }
}
