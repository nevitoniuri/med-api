package med.voll.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.voll.api.model.dto.MedicoDTO;
import med.voll.api.repository.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository repository;

    @Transactional
    public void cadastrar(MedicoDTO medico) {
        repository.save(medico.toEntity());
    }

}
