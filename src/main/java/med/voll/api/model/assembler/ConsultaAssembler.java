package med.voll.api.model.assembler;

import lombok.RequiredArgsConstructor;
import med.voll.api.model.Consulta;
import med.voll.api.model.request.ConsultaCreate;
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
        var date = LocalDateTime.parse(consultaCreate.dataHora(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).truncatedTo(ChronoUnit.HOURS);
        var consulta = Consulta.builder()
                .paciente(paciente)
                .dataHora(date)
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
