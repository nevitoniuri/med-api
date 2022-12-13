package med.voll.api.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.voll.api.controller.filter.PacienteFilter;
import med.voll.api.exception.ResourceDuplicatedException;
import med.voll.api.exception.ResourceNotFoundException;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.repository.spec.PacienteSpec;
import med.voll.api.service.PacienteService;
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
