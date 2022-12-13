package med.voll.api.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import med.voll.api.exception.ResourceNotFoundException;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Status {
    INATIVO(0), ATIVO(1);

    private final Integer id;

    public static Status of(Integer value) {
        return Stream.of(Status.values())
                .filter(status -> status.id.equals(value))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Status não encontrado"));
    }

    public static boolean toBoolean(Integer value) {
        return value == 1;
    }
}
