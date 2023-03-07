package med.api.service.validation;

import java.time.LocalDateTime;

public interface ConsultaHorarioValidation {

    void validateDataHora(LocalDateTime dataHora);

}
