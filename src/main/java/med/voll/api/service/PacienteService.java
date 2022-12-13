package med.voll.api.service;

import med.voll.api.controller.filter.PacienteFilter;
import med.voll.api.model.Paciente;

public interface PacienteService extends CrudService<Paciente, PacienteFilter> {

    void activate(Paciente paciente);

    void deactivate(Paciente paciente);

}
