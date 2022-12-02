package med.voll.api.model.dto;

import lombok.Builder;
import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;

@Builder
public record MedicoDTO(Long id, String nome, String crm, String email, String telefone, Especialidade especialidade,
                        Endereco endereco) {
}
