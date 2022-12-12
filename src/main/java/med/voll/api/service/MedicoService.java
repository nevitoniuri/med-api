package med.voll.api.service;

import med.voll.api.controller.filter.MedicoFilter;
import med.voll.api.model.Medico;

public interface MedicoService extends CrudService<Medico, MedicoFilter> {

    void activate(Medico medico);

    void deactivate(Medico medico);

}
