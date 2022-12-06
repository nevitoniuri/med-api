package med.voll.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import med.voll.api.exception.ResourceNotFoundException;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository repository;
    private final ConsultaService consultaService;

    public Medico findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
    }

    public Page<Medico> list(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable);
    }

    @Transactional
    public void save(Medico medico) {
        repository.save(medico);
    }

    @Transactional
    public void activate(Medico medico) {
        if (medico.isAtivo()) {
            throw new IllegalArgumentException("Médico já está ativo");
        }
        medico.activate();
        save(medico);
    }

    @Transactional
    public void deactivate(Medico medico) {
        if (!medico.isAtivo()) {
            throw new IllegalArgumentException("Médico já está inativo");
        }
        medico.deactivate();
        save(medico);
    }

    public Medico findAvailableByEspecialidadeAndDataHora(Especialidade especialidade, LocalDateTime dataHora) {
        var medicos = repository.findAllByAtivoTrueAndEspecialidade(especialidade);
        return medicos.stream()
                .sorted()
                .filter(medico -> isAvailableInDataHora(medico, dataHora))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
    }

    public boolean isAvailableInDataHora(Medico medico, LocalDateTime dataHora) {
        return !consultaService.existeConsultaComMedicoNoHorario(medico.getId(), dataHora);
    }

}
