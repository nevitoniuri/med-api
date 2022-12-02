package med.voll.api.model.assembler;

import med.voll.api.model.Medico;
import med.voll.api.model.dto.MedicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicoAssembler {

    public Medico toEntity(MedicoDTO medicoDTO) {
        return Medico.builder()
                .nome(medicoDTO.nome())
                .crm(medicoDTO.crm())
                .email(medicoDTO.email())
                .telefone(medicoDTO.telefone())
                .especialidade(medicoDTO.especialidade())
                .endereco(medicoDTO.endereco())
                .build();
    }

    public List<MedicoDTO> toDTO(List<Medico> medicos) {
        return medicos.stream().map(this::toDTO).toList();
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
