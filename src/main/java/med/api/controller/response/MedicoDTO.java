package med.api.controller.response;

import lombok.Builder;
import med.api.model.Endereco;
import med.api.model.Especialidade;

@Builder
public record MedicoDTO(Long id, boolean ativo, String nome, String crm, String email, String telefone, Especialidade especialidade,
                        Endereco endereco) {
}
