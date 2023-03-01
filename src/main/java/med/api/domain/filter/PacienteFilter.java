package med.api.domain.filter;

import java.util.List;

public record PacienteFilter(Long id, List<Integer> status, String nome, String cpf, String email, String telefone) {
}
