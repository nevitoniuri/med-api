package med.api.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import med.api.domain.enums.Especialidade;
import med.api.domain.filter.MedicoFilter;
import med.api.domain.model.Medico;
import med.api.exception.ResourceDuplicatedException;
import med.api.exception.ResourceNotFoundException;
import med.api.repository.MedicoRepository;
import med.api.repository.spec.MedicoSpec;
import med.api.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository repository;

    public Medico findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
    }

    public Page<Medico> list(MedicoFilter filter, Pageable pageable) {
        return repository.findAll(MedicoSpec.filter(filter), pageable);
    }

    @Transactional
    public void save(Medico medico) {
        if (alreadyExists(medico)) {
            throw new ResourceDuplicatedException("Médico já cadastrado");
        }
        repository.save(medico);
    }

    private boolean alreadyExists(Medico medico) {
        return repository.exists(MedicoSpec.exists(medico));
    }

    @Transactional
    public void activate(Medico medico) {
        if (medico.isAtivo()) {
            throw new IllegalArgumentException("Médico já está ativo");
        }
        medico.activate();
        repository.save(medico);
    }

    @Transactional
    public void deactivate(Medico medico) {
        if (!medico.isAtivo()) {
            throw new IllegalArgumentException("Médico já está inativo");
        }
        medico.deactivate();
        repository.save(medico);
    }

    @SneakyThrows
    public Medico findAvailableByEspecialidadeAndDataHora(Especialidade especialidade, LocalDateTime dataHora) {
        var medicos = repository.findAllByAtivoTrueAndEspecialidade(especialidade);
        Collections.shuffle(medicos);
        var medicosDisponiveis = medicos.stream()
                .filter(medico -> isAvailableInDataHora(medico, dataHora))
                .toList();
        if (medicosDisponiveis.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum médico disponível para esta especialidade neste horário");
        }
        var rand = SecureRandom.getInstanceStrong();
        return medicosDisponiveis.get(rand.nextInt(medicosDisponiveis.size()));
    }

    private boolean isAvailableInDataHora(Medico medico, LocalDateTime dataHora) {
        return repository.isAvailableInDataHora(medico.getId(), dataHora);
    }

}
