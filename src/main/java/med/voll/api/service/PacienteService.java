package med.voll.api.service;

import med.voll.api.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteService {

    Paciente findById(Long id);

    Page<Paciente> list(Pageable pageable);

    void save(Paciente paciente);

    void activate(Paciente paciente);

    void deactivate(Paciente paciente);

}
