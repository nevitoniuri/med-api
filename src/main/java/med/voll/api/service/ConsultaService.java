package med.voll.api.service;

import med.voll.api.model.Consulta;
import med.voll.api.model.MotivoCancelamento;
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
