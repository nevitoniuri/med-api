package med.api.repository;

import med.api.model.Consulta;
import med.api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteAndDataHora(Paciente paciente, LocalDateTime dataHora);

    boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);
}
