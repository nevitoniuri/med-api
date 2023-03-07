package med.api.repository;

import med.api.domain.enums.Especialidade;
import med.api.domain.model.Medico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicoRepository extends CustomJpaRepository<Medico, Long> {

    List<Medico> findAllByAtivoTrueAndEspecialidade(Especialidade especialidade);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.id = :id
            AND m.ativo = true
            AND NOT EXISTS (SELECT c FROM Consulta c WHERE c.medico = m AND c.dataHora = :dataHora)
            """)
    boolean isAvailableInDataHora(Long id, LocalDateTime dataHora);

}
