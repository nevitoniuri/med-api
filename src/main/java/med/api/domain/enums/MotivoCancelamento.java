package med.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MotivoCancelamento {
    NAO_COMPARECIMENTO(1L, "Não comparecimento"),
    CANCELAMENTO_MEDICO(2L, "Cancelamento médico"),
    CANCELAMENTO_PACIENTE(3L, "Cancelamento paciente");

    private final Long id;
    private final String motivo;
}
