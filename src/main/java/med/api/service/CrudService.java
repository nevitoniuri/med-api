package med.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<E, F> {

    E findById(Long id);

    Page<E> list(F filter, Pageable pageable);

    void save(E entity);

}
