package med.voll.api.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import med.voll.api.exception.ResourceDuplicatedException;
import med.voll.api.exception.ResourceNotFoundException;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.spec.MedicoSpec;
import med.voll.api.service.MedicoService;
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
    private final ConsultaServiceImpl consultaService;

    public Medico findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
    }

    public Page<Medico> list(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable);
    }

    @Transactional
    public void save(Medico medico) {
        if (alreadyExists(medico)) {
            throw new ResourceDuplicatedException("Médico já cadastrado");
        }
        repository.save(medico);
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

    public boolean alreadyExists(Medico medico) {
        return repository.exists(MedicoSpec.exists(medico));
    }

    @SneakyThrows
    public Medico findAvailableByEspecialidadeAndDataHora(Especialidade especialidade, LocalDateTime dataHora) {
        var medicos = repository.findAllByAtivoTrueAndEspecialidade(especialidade);
        Collections.shuffle(medicos);
        var medicosDisponiveis = medicos.stream()
                .filter(medico -> isAvailableInDataHora(medico, dataHora))
                .toList();
        if (medicosDisponiveis.isEmpty()) {
            throw new ResourceNotFoundException("Médico não encontrado");
        }
        var rand = SecureRandom.getInstanceStrong();
        return medicosDisponiveis.get(rand.nextInt(medicosDisponiveis.size()));
    }

    public boolean isAvailableInDataHora(Medico medico, LocalDateTime dataHora) {
        return !consultaService.existeConsultaComMedicoNoHorario(medico.getId(), dataHora);
    }

}
