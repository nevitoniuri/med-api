package med.voll.api.controller;

import lombok.RequiredArgsConstructor;
import med.voll.api.controller.assembler.PacienteAssembler;
import med.voll.api.controller.request.PacienteCreate;
import med.voll.api.controller.request.PacienteUpdate;
import med.voll.api.controller.response.PacienteDTO;
import med.voll.api.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static med.voll.api.common.ControllerURIs.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PACIENTES)
public class PacienteController {

    private final PacienteService service;
    private final PacienteAssembler assembler;

    @GetMapping(ID)
    public PacienteDTO findById(@PathVariable Long id) {
        return assembler.toDTO(service.findById(id));
    }

    //TODO: add query params
    @GetMapping
    public Page<PacienteDTO> list(@PageableDefault(sort = "nome") Pageable pageable) {
        return assembler.toDTO(service.list(pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PacienteCreate paciente) {
        service.save(assembler.toEntity(paciente));
    }

    @PutMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody PacienteUpdate pacienteUpdate) {
        var paciente = service.findById(id);
        assembler.updateEntity(pacienteUpdate, paciente);
        service.save(paciente);
    }

    @PutMapping(ID_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        var paciente = service.findById(id);
        service.activate(paciente);
    }

    @PutMapping(ID_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        var paciente = service.findById(id);
        service.deactivate(paciente);
    }

}
