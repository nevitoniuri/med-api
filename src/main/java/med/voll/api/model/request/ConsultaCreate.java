package med.voll.api.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConsultaCreate(
        Long medicoId,
        @NotNull
        Long pacienteId,
        @NotBlank
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        String dataHora) {
}
