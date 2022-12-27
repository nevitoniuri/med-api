package med.api.service;

import med.api.controller.filter.PacienteFilter;
import med.api.domain.model.Paciente;

public interface PacienteService extends CrudService<Paciente, PacienteFilter> {

    void activate(Paciente paciente);

    void deactivate(Paciente paciente);

}
