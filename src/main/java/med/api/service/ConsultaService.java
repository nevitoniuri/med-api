package med.api.service;

import med.api.domain.model.Consulta;
import med.api.domain.enums.MotivoCancelamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ConsultaService {

    Consulta findById(Long id);

    Page<Consulta> list(Pageable pageable);

    void save(Consulta consulta);

    void reagendar(Consulta consulta, LocalDateTime dataHora);

    void cancelar(Consulta consulta, MotivoCancelamento motivo);

}
