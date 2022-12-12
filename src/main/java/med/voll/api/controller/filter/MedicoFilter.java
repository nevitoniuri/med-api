package med.voll.api.controller.filter;

import java.util.List;

public record MedicoFilter(Long id, Boolean ativo, String nome, String crm, String email, String telefone,
                           List<Long> especialidade) {
}
