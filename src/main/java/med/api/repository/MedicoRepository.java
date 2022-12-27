package med.api.repository;

import med.api.domain.enums.Especialidade;
import med.api.domain.model.Medico;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends CustomJpaRepository<Medico, Long> {

    List<Medico> findAllByAtivoTrueAndEspecialidade(Especialidade especialidade);

}
