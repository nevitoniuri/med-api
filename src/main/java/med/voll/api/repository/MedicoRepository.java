package med.voll.api.repository;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends CustomJpaRepository<Medico, Long> {

    List<Medico> findAllByAtivoTrueAndEspecialidade(Especialidade especialidade);

}
