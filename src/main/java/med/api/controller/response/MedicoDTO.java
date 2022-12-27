package med.api.controller.response;

import lombok.Builder;
import med.api.domain.model.Endereco;
import med.api.domain.enums.Especialidade;

@Builder
public record MedicoDTO(Long id, boolean ativo, String nome, String crm, String email, String telefone, Especialidade especialidade,
                        Endereco endereco) {
}
