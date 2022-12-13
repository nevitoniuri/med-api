package med.voll.api.controller.filter;

import java.util.List;

public record MedicoFilter(Long id, List<Integer> status, String nome, String crm, String email, String telefone,
                           List<Integer> especialidade) {
}
