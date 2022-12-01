package med.voll.api.model.dto;

import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

public record MedicoDTO(String nome, String crm, String email, String telefone, Especialidade especialidade,
                        Endereco endereco) {
    public Medico toEntity() {
        return Medico.builder()
                .nome(this.nome)
                .crm(this.crm)
                .email(this.email)
                .telefone(this.telefone)
                .especialidade(this.especialidade)
                .endereco(this.endereco)
                .build();
    }
}
