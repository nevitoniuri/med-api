package med.voll.api.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import med.voll.api.model.Endereco;

@Builder
public record MedicoUpdate(
        String nome,
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @Valid
        Endereco endereco) {
}
