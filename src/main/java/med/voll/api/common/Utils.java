package med.voll.api.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

    public static LocalDateTime handleDataHora(String dataHora) {
        return LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).truncatedTo(ChronoUnit.HOURS);
    }

    public static String handleTelefone(String telefone) {
        return telefone.replaceAll("\\D", "");
    }

}
