package med.voll.api.model.dto;

import lombok.Builder;
import med.voll.api.model.Endereco;

@Builder
public record PacienteDTO(String nome, String cpf, String email, String telefone, Endereco endereco) {
}
