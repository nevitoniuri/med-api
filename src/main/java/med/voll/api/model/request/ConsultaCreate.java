package med.voll.api.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Especialidade;

public record ConsultaCreate(
        Long medicoId,
        Especialidade especialidade,
        @NotNull
        Long pacienteId,
        @NotBlank
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        String dataHora) {
}
