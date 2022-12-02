package med.voll.api.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.Endereco;

public record MedicoUpdate(
        String nome,
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @Valid
        Endereco endereco) {
}
