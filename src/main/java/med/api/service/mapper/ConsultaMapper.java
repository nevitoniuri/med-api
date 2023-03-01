package med.api.service.mapper;

import lombok.RequiredArgsConstructor;
import med.api.controller.request.ConsultaCreate;
import med.api.domain.model.Consulta;
import med.api.domain.enums.StatusConsulta;
import med.api.service.PacienteService;
import med.api.service.impl.MedicoServiceImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

import static med.api.common.Utils.handleDataHora;

@Component
@RequiredArgsConstructor
public class ConsultaMapper {

    private final MedicoServiceImpl medicoService;
    private final PacienteService pacienteService;

    public Consulta toEntity(ConsultaCreate consultaCreate) {

        if (isRequestInvalid(consultaCreate)) {
            throw new IllegalArgumentException("Request inválido");
        }

        var paciente = pacienteService.findById(consultaCreate.pacienteId());
        var consulta = Consulta.builder()
                .status(StatusConsulta.AGENDADA)
                .paciente(paciente)
                .dataHora(handleDataHora(consultaCreate.dataHora()))
                .dataHoraCriacao(LocalDateTime.now())
                .build();

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

}
