package med.voll.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
}
