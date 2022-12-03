package med.voll.api.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.Endereco;

public record MedicoUpdate(
        @Email
        String email,
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @Valid
        Endereco endereco) {
}
