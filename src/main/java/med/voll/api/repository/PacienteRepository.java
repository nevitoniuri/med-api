package med.voll.api.repository;

import med.voll.api.model.Paciente;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends CustomJpaRepository<Paciente, Long> {
}
