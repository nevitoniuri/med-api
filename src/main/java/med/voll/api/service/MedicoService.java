package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.model.dto.MedicoDTO;
import med.voll.api.repository.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository repository;

    public void cadastrar(MedicoDTO medico) {
        repository.save(medico.toEntity());
    }

}
