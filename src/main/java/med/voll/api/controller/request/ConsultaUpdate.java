package med.voll.api.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import med.voll.api.model.MotivoCancelamento;

public record ConsultaUpdate(
        MotivoCancelamento motivoCancelamento,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        String dataHora
) {
}
