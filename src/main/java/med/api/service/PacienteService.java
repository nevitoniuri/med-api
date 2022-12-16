package med.api.service;

import med.api.controller.filter.PacienteFilter;
import med.api.model.Paciente;

public interface PacienteService extends CrudService<Paciente, PacienteFilter> {

    void activate(Paciente paciente);

    void deactivate(Paciente paciente);

}
