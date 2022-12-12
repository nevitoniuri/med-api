package med.voll.api.service;

import med.voll.api.model.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsultaService {

    Consulta findById(Long id);

    Page<Consulta> list(Pageable pageable);

    void save(Consulta consulta);

//    void cancelar(Consulta consulta);
//
//    void reagendar(Consulta consulta);

}
