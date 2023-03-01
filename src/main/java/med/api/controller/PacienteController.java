package med.api.controller;

import lombok.RequiredArgsConstructor;
import med.api.common.Constantes;
import med.api.controller.filter.PacienteFilter;
import med.api.controller.request.PacienteCreate;
import med.api.controller.request.PacienteUpdate;
import med.api.controller.response.PacienteDTO;
import med.api.service.PacienteService;
import med.api.service.mapper.PacienteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constantes.PACIENTES)
public class PacienteController {

    private final PacienteService service;
    private final PacienteMapper mapper;

    @GetMapping
    public Page<PacienteDTO> list(PacienteFilter filter, Pageable pageable) {
        return service.list(filter, pageable).map(mapper::toDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PacienteCreate paciente) {
        service.save(mapper.toEntity(paciente));
    }

    @PutMapping(Constantes.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody PacienteUpdate pacienteUpdate) {
        var paciente = service.findById(id);
        mapper.updateEntity(pacienteUpdate, paciente);
        service.save(paciente);
    }

    @PutMapping(Constantes.ID_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        service.activate(service.findById(id));
    }

    @PutMapping(Constantes.ID_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        service.deactivate(service.findById(id));
    }

}
