package med.api.controller.response;

import lombok.Builder;
import med.api.domain.model.Endereco;

@Builder
public record PacienteDTO(Long id, boolean ativo, String nome, String cpf, String email, String telefone, Endereco endereco) {
}
