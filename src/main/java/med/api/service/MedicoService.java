package med.api.service;

import med.api.controller.filter.MedicoFilter;
import med.api.model.Medico;

public interface MedicoService extends CrudService<Medico, MedicoFilter> {

    void activate(Medico medico);

    void deactivate(Medico medico);

}
