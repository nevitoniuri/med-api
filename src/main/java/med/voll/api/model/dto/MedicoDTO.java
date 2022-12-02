package med.voll.api.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;

@Builder
public record MedicoDTO(

        Long id,
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        Endereco endereco) {
}
