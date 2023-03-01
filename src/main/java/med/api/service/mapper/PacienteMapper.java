package med.api.service.mapper;

import med.api.common.Utils;
import med.api.domain.request.PacienteCreate;
import med.api.domain.request.PacienteUpdate;
import med.api.domain.response.PacienteDTO;
import med.api.domain.model.Paciente;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PacienteMapper {

    public PacienteDTO toDTO(Paciente paciente) {
        return PacienteDTO.builder()
                .id(paciente.getId())
                .ativo(paciente.isAtivo())
                .nome(paciente.getNome())
                .cpf(paciente.getCpf())
                .email(paciente.getEmail())
                .telefone(paciente.getTelefone())
                .endereco(paciente.getEndereco())
                .build();
    }

    public Paciente toEntity(PacienteCreate pacienteCreate) {
        return Paciente.builder()
                .nome(pacienteCreate.nome())
                .cpf(pacienteCreate.cpf())
                .email(pacienteCreate.email())
                .telefone(Utils.handleTelefone(pacienteCreate.telefone()))
                .endereco(pacienteCreate.endereco())
                .build();
    }

    //TODO: como refatorar?
    public void updateEntity(PacienteUpdate pacienteUpdate, Paciente paciente) {
        if (Objects.nonNull(pacienteUpdate.nome())) {
            paciente.setNome(pacienteUpdate.nome().trim());
        }
        if (Objects.nonNull(pacienteUpdate.telefone())) {
            paciente.setTelefone(Utils.handleTelefone(pacienteUpdate.telefone()));
        }
        if (Objects.nonNull(pacienteUpdate.endereco())) {
            paciente.setEndereco(pacienteUpdate.endereco());
        }
    }

}
