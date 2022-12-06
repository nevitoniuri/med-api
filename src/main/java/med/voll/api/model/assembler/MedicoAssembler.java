package med.voll.api.model.assembler;

import med.voll.api.model.Medico;
import med.voll.api.model.dto.MedicoDTO;
import med.voll.api.model.request.MedicoCreate;
import med.voll.api.model.request.MedicoUpdate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MedicoAssembler {

    public Medico toEntity(MedicoCreate medicoCreate) {
        return Medico.builder()
                .nome(medicoCreate.nome())
                .crm(medicoCreate.crm())
                .email(medicoCreate.email())
                .telefone(medicoCreate.telefone())
                .especialidade(medicoCreate.especialidade())
                .endereco(medicoCreate.endereco())
                .build();
    }

    //TODO: como refatorar?
    public void updateEntity(MedicoUpdate medicoUpdate, Medico medico) {
        if (Objects.nonNull(medicoUpdate.nome())) {
            medico.setNome(medicoUpdate.nome());
        }
        if (Objects.nonNull(medicoUpdate.telefone())) {
            medico.setTelefone(medicoUpdate.telefone());
        }
        if (Objects.nonNull(medicoUpdate.endereco())) {
            medico.setEndereco(medicoUpdate.endereco());
        }
    }

    public Page<MedicoDTO> toDTO(Page<Medico> medicos) {
        return medicos.map(this::toDTO);
    }

    public MedicoDTO toDTO(Medico medico) {
        return MedicoDTO.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .crm(medico.getCrm())
                .email(medico.getEmail())
                .telefone(medico.getTelefone())
                .especialidade(medico.getEspecialidade())
                .endereco(medico.getEndereco())
                .build();
    }

}
