package med.voll.api.service;

import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicoServiceInterface {

    Medico findById(Long id);

    Page<Medico> list(Pageable pageable);

    void save(Medico medico);

    void activate(Medico medico);

    void deactivate(Medico medico);


}
