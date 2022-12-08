package med.voll.api.controller.response;

import lombok.Builder;
import med.voll.api.model.Endereco;

@Builder
public record PacienteDTO(Long id, boolean ativo, String nome, String cpf, String email, String telefone, Endereco endereco) {
}
