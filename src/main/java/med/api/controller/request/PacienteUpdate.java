package med.api.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import med.api.domain.model.Endereco;

@Builder
public record PacienteUpdate(
        String nome,
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @Valid
        Endereco endereco) {
}
