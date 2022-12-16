package med.api.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.api.controller.filter.PacienteFilter;
import med.api.exception.ResourceDuplicatedException;
import med.api.exception.ResourceNotFoundException;
import med.api.model.Paciente;
import med.api.repository.PacienteRepository;
import med.api.repository.spec.PacienteSpec;
import med.api.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;

    public Paciente findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
    }

    public Page<Paciente> list(PacienteFilter filter, Pageable pageable) {
        return repository.findAll(PacienteSpec.filter(filter), pageable);
    }

    @Transactional
    public void save(Paciente paciente) {
        if (alreadyExists(paciente)) {
            throw new ResourceDuplicatedException("Paciente já cadastrado");
        }
        repository.save(paciente);
    }

    public boolean alreadyExists(Paciente paciente) {
        return repository.exists(PacienteSpec.exists(paciente));
    }

    @Transactional
    public void activate(Paciente paciente) {
        if (paciente.isAtivo()) {
            throw new IllegalArgumentException("Paciente já está ativo");
        }
        paciente.activate();
        save(paciente);
    }

    @Transactional
    public void deactivate(Paciente paciente) {
        if (!paciente.isAtivo()) {
            throw new IllegalArgumentException("Paciente já está inativo");
        }
        paciente.deactivate();
        save(paciente);
    }
}
