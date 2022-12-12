package med.voll.api.repository;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends CustomJpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    List<Medico> findAllByAtivoTrueAndEspecialidade(Especialidade especialidade);

}
