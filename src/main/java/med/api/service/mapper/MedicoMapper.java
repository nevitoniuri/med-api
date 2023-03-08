package med.api.service.mapper;

import med.api.domain.response.MedicoResponse;
import med.api.common.Utils;
import med.api.domain.model.Medico;
import med.api.domain.request.MedicoCreate;
import med.api.domain.request.MedicoUpdate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MedicoMapper {

    public Medico toEntity(MedicoCreate medicoCreate) {
        return Medico.builder()
                .nome(medicoCreate.nome().trim())
                .crm(medicoCreate.crm())
                .email(medicoCreate.email())
                .telefone(Utils.handleTelefone(medicoCreate.telefone()))
                .especialidade(medicoCreate.especialidade())
                .endereco(medicoCreate.endereco())
                .build();
    }

    //TODO: como refatorar?
    public void updateEntity(MedicoUpdate medicoUpdate, Medico medico) {
        if (Objects.nonNull(medicoUpdate.nome())) {
            medico.setNome(medicoUpdate.nome().trim());
        }
        if (Objects.nonNull(medicoUpdate.telefone())) {
            medico.setTelefone(Utils.handleTelefone(medicoUpdate.telefone()));
        }
        if (Objects.nonNull(medicoUpdate.endereco())) {
            medico.setEndereco(medicoUpdate.endereco());
        }
    }

    public MedicoResponse toDTO(Medico medico) {
        return MedicoResponse.builder()
                .id(medico.getId())
                .ativo(medico.isAtivo())
                .nome(medico.getNome())
                .crm(medico.getCrm())
                .email(medico.getEmail())
                .telefone(medico.getTelefone())
                .especialidade(medico.getEspecialidade())
                .endereco(medico.getEndereco())
                .build();
    }

}
