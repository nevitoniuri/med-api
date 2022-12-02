package med.voll.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.voll.api.exception.ResourceNotFoundException;
import med.voll.api.model.Paciente;
import med.voll.api.model.dto.PacienteDTO;
import med.voll.api.repository.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    @Transactional
    public void cadastrar(PacienteDTO paciente) {
        repository.save(paciente.toEntity());
    }

    public Paciente findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
    }
}
