package med.voll.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.voll.api.exception.ResourceNotFoundException;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
    }

    public Page<Paciente> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public void save(Paciente paciente) {
        repository.save(paciente);
    }

}
