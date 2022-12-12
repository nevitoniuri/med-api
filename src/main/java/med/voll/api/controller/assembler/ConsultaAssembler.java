package med.voll.api.controller.assembler;

import lombok.RequiredArgsConstructor;
import med.voll.api.controller.request.ConsultaCreate;
import med.voll.api.model.Consulta;
import med.voll.api.model.StatusConsulta;
import med.voll.api.service.impl.MedicoServiceImpl;
import med.voll.api.service.impl.PacienteServiceImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

import static med.voll.api.common.Utils.handleDataHora;

@Component
@RequiredArgsConstructor
public class ConsultaAssembler {

    private final MedicoServiceImpl medicoService;
    private final PacienteServiceImpl pacienteService;

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

}
