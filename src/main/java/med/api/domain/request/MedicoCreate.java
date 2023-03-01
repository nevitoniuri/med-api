package med.api.domain.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import med.api.domain.model.Endereco;
import med.api.domain.enums.Especialidade;

@Builder
public record MedicoCreate(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        Endereco endereco) {
}