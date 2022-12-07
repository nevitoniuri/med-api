package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.controller.assembler.MedicoAssembler;
import med.voll.api.controller.response.MedicoDTO;
import med.voll.api.controller.request.MedicoCreate;
import med.voll.api.controller.request.MedicoUpdate;
import med.voll.api.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("medicos")
public class MedicoController {

    private final MedicoService service;
    private final MedicoAssembler assembler;

    @GetMapping("{id}")
    public MedicoDTO findById(@PathVariable Long id) {
        return assembler.toDTO(service.findById(id));
    }

    @GetMapping
    public Page<MedicoDTO> list(@PageableDefault(sort = "nome") Pageable pageable) {
        return assembler.toDTO(service.list(pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid MedicoCreate medico) {
        service.save(assembler.toEntity(medico));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid MedicoUpdate medicoUpdate) {
        var medico = service.findById(id);
        assembler.updateEntity(medicoUpdate, medico);
        service.save(medico);
    }

    @PutMapping("{id}/activate")
    public void activate(@PathVariable Long id) {
        var medico = service.findById(id);
        service.activate(medico);
    }

    @PutMapping("{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        var medico = service.findById(id);
        service.deactivate(medico);
    }

}
