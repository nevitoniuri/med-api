package med.api.controller;

import lombok.RequiredArgsConstructor;
import med.api.domain.filter.PacienteFilter;
import med.api.domain.request.PacienteCreate;
import med.api.domain.request.PacienteUpdate;
import med.api.domain.response.PacienteDTO;
import med.api.service.PacienteService;
import med.api.service.mapper.PacienteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static med.api.common.Constantes.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PACIENTES)
public class PacienteController {

    private final PacienteService service;
    private final PacienteMapper mapper;

    @GetMapping
    public Page<PacienteDTO> list(PacienteFilter filter, Pageable pageable) {
        return service.list(filter, pageable).map(mapper::toDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //TODO: Implementar CreatedResource, para retornar o Location do recurso criado
    public void create(@RequestBody PacienteCreate paciente) {
        service.save(mapper.toEntity(paciente));
    }

    @PutMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody PacienteUpdate pacienteUpdate) {
        var paciente = service.findById(id);
        mapper.updateEntity(pacienteUpdate, paciente);
        service.save(paciente);
    }

    @PutMapping(ID_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        service.activate(service.findById(id));
    }

    @PutMapping(ID_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        service.deactivate(service.findById(id));
    }

}
