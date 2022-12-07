package med.voll.api.controller.assembler;

import lombok.RequiredArgsConstructor;
import med.voll.api.model.Consulta;
import med.voll.api.model.StatusConsulta;
import med.voll.api.controller.request.ConsultaCreate;
import med.voll.api.controller.request.ConsultaUpdate;
import med.voll.api.service.MedicoService;
import med.voll.api.service.PacienteService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ConsultaAssembler {

    private final MedicoService medicoService;
    private final PacienteService pacienteService;

    public Consulta toEntity(ConsultaCreate consultaCreate) {
        var paciente = pacienteService.findById(consultaCreate.pacienteId());
        var consulta = Consulta.builder()
                .status(StatusConsulta.AGENDADA)
                .paciente(paciente)
                .dataHora(handleDataHora(consultaCreate.dataHora()))
                .dataHoraCriacao(LocalDateTime.now())
                .build();

        if (isRequestInvalid(consultaCreate)) {
            throw new IllegalArgumentException("Request inválido");
        }

        if (Objects.nonNull(consultaCreate.medicoId())) {
            consulta.setMedico(medicoService.findById(consultaCreate.medicoId()));
        } else {
            consulta.setMedico(medicoService.findAvailableByEspecialidadeAndDataHora(consultaCreate.especialidade(), consulta.getDataHora()));
        }
        return consulta;
    }

    public boolean isRequestInvalid(ConsultaCreate consultaCreate) {
        return Objects.isNull(consultaCreate.medicoId()) && Objects.isNull(consultaCreate.especialidade());
    }

    //Trabalhar com a entidade aqui, ou levar para o Service?
    public void updateEntity(ConsultaUpdate consultaUpdate, Consulta consulta) {
        if (Objects.nonNull(consultaUpdate.dataHora())) {
            consulta.setDataHora(handleDataHora(consultaUpdate.dataHora()));
            consulta.setStatus(StatusConsulta.REAGENDADA);
        }
        if (Objects.nonNull(consultaUpdate.motivoCancelamento())) {
            consulta.setMotivoCancelamento(consultaUpdate.motivoCancelamento());
            consulta.setStatus(StatusConsulta.CANCELADA);
        }
    }

    private static LocalDateTime handleDataHora(String dataHora) {
        return LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).truncatedTo(ChronoUnit.HOURS);
    }

}
