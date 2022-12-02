package med.voll.api.model.assembler;

import med.voll.api.model.Paciente;
import med.voll.api.model.dto.PacienteDTO;
import org.springframework.stereotype.Component;

@Component
public class PacienteAssembler {
    public PacienteDTO toDTO(Paciente paciente) {
        return PacienteDTO.builder()
                .nome(paciente.getNome())
                .cpf(paciente.getCpf())
                .email(paciente.getEmail())
                .telefone(paciente.getTelefone())
                .endereco(paciente.getEndereco())
                .build();
    }
}
