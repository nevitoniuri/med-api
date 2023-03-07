package med.api.service.validation.impl;

import med.api.exception.InvalidDataException;
import med.api.service.validation.ConsultaHorarioValidation;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static med.api.common.Constantes.FIM_DIA;
import static med.api.common.Constantes.INICIO_DIA;

@Component
public class ConsultaHorarioValidationImpl implements ConsultaHorarioValidation {

    @Override
    public void validateDataHora(LocalDateTime dataHora) {
        if (isLessThan30MinutesAntecedence(dataHora)) {
            throw new InvalidDataException("Consulta deve ser agendada com pelo menos 30 minutos de antecedência");
        }
        if (isMoreThan30DaysAntecedence(dataHora)) {
            throw new InvalidDataException("Consulta deve ser agendada com no máximo 30 dias de antecedência");
        }
        if (isHoraInvalid(dataHora)) {
            throw new InvalidDataException("Consulta deve ser agendada entre 7h e 19h");
        }
        if (isDomingo(dataHora)) {
            throw new InvalidDataException("Consulta não pode ser agendada para domingo");
        }
    }

    private boolean isLessThan30MinutesAntecedence(LocalDateTime dataHora) {
        return dataHora.isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private boolean isMoreThan30DaysAntecedence(LocalDateTime dataHora) {
        return dataHora.isAfter(LocalDateTime.now().plusDays(30));
    }

    private boolean isHoraInvalid(LocalDateTime dataHora) {
        return dataHora.getHour() < INICIO_DIA || dataHora.getHour() > FIM_DIA;
    }

    private boolean isDomingo(LocalDateTime dataHora) {
        return dataHora.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

}
