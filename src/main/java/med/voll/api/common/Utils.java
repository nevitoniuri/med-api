package med.voll.api.common;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

    public static LocalDateTime handleDataHora(String dataHora) {
        return LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).truncatedTo(ChronoUnit.HOURS);
    }
}
