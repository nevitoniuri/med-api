package med.voll.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.voll.api.exception.ResourceNotFoundException;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository repository;

    public Medico findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
    }

    public Page<Medico> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public void save(Medico medico) {
        repository.save(medico);
    }

}
