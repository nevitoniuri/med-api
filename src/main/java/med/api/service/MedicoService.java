package med.api.service;

import med.api.domain.filter.MedicoFilter;
import med.api.domain.model.Medico;

public interface MedicoService extends CrudService<Medico, MedicoFilter> {

    void activate(Medico medico);

    void deactivate(Medico medico);

}
