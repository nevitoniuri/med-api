package med.voll.api.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import med.voll.api.exception.ResourceNotFoundException;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Especialidade {
    ORTOPEDIA(1), CARDIOLOGIA(2), GINECOLOGIA(3), DERMATOLOGIA(4);

    private final Integer id;

    public static Especialidade of(Integer id) {
        return Stream.of(Especialidade.values())
                .filter(especialidade -> especialidade.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));
    }

}
