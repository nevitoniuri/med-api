package med.voll.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusConsulta {
    AGENDADA(1L, "Agendada"),
    REAGENDADA(2L, "Reagendada"),
    REALIZADA(3L, "Realizada"),
    CANCELADA(4L, "Cancelada");

    private final Long id;
    private final String descricao;
}
