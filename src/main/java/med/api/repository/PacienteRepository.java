package med.api.repository;

import med.api.model.Paciente;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends CustomJpaRepository<Paciente, Long> {
}
