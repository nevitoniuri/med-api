package med.voll.api.controller;

import lombok.RequiredArgsConstructor;
import med.voll.api.model.assembler.PacienteAssembler;
import med.voll.api.model.dto.PacienteDTO;
import med.voll.api.model.request.PacienteCreate;
import med.voll.api.model.request.PacienteUpdate;
import med.voll.api.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("pacientes")
public class PacienteController {

    private final PacienteService service;
    private final PacienteAssembler assembler;

    @GetMapping("{id}")
    public PacienteDTO findById(@PathVariable Long id) {
        return assembler.toDTO(service.findById(id));
    }

    @GetMapping
    public Page<PacienteDTO> list(@PageableDefault(sort = "nome") Pageable pageable) {
        return assembler.toDTO(service.list(pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PacienteCreate paciente) {
        service.save(assembler.toEntity(paciente));
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody PacienteUpdate pacienteUpdate) {
        var paciente = service.findById(id);
        assembler.updateEntity(pacienteUpdate, paciente);
        service.save(paciente);
    }

    @PutMapping("{id}/activate")
    public void activate(@PathVariable Long id) {
        var paciente = service.findById(id);
        service.activate(paciente);
    }

    @PutMapping("{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        var paciente = service.findById(id);
        service.deactivate(paciente);
    }

}
