package med.voll.api.model.assembler;

import lombok.RequiredArgsConstructor;
import med.voll.api.model.Consulta;
import med.voll.api.model.request.ConsultaCreate;
import med.voll.api.service.MedicoService;
import med.voll.api.service.PacienteService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ConsultaAssembler {

    private final MedicoService medicoService;
    private final PacienteService pacienteService;

    public Consulta toEntity(ConsultaCreate consultaCreate) {
        var consulta = Consulta.builder()
                .paciente(pacienteService.findById(consultaCreate.pacienteId()))
                .dataHora(LocalDateTime.parse(consultaCreate.dataHora(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .dataHoraCriacao(LocalDateTime.now())
                .build();

        if (Objects.isNull(consultaCreate.medicoId())) {
            consulta.setMedico(medicoService.findAvailableByEspecialidade(consultaCreate.especialidade()));
        }
        return consulta;
    }
}
