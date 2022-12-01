package med.voll.api.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Especialidade {
    ORTOPEDIA(1L, "Ortopedia"),
    CARDIOLOGIA(2L, "Cardiologia"),
    GINECOLOGIA(3L, "Ginecologia"),
    DERMATOLOGIA(4L, "Dermatologia");

    private final Long id;
    private final String descricao;

}
