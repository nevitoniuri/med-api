package med.voll.api.model.dto;

import med.voll.api.model.Endereco;

public record PacienteDTO(String nome, String cpf, String email, String telefone, Endereco endereco) {
}
