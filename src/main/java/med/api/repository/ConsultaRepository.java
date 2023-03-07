package med.api.repository;

import med.api.domain.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataHoraBetween(Long pacienteId, LocalDateTime inicio, LocalDateTime fim);

}
