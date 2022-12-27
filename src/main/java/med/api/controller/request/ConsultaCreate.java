package med.api.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import med.api.domain.enums.Especialidade;

@Builder
public record ConsultaCreate(
        Long medicoId,
        Especialidade especialidade,
        @NotNull
        Long pacienteId,
        @NotBlank
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        String dataHora) {
}
