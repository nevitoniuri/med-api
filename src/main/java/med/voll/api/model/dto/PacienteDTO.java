package med.voll.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import med.voll.api.model.Endereco;
import med.voll.api.model.Paciente;

@Builder
public record PacienteDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefone,
        @NotNull
        Endereco endereco) {

    public Paciente toEntity() {
        return Paciente.builder()
                .nome(nome)
                .cpf(cpf)
                .email(email)
                .telefone(telefone)
                .endereco(endereco)
                .build();
    }
}
