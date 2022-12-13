package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.controller.assembler.MedicoAssembler;
import med.voll.api.controller.filter.MedicoFilter;
import med.voll.api.controller.request.MedicoCreate;
import med.voll.api.controller.request.MedicoUpdate;
import med.voll.api.controller.response.MedicoDTO;
import med.voll.api.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static med.voll.api.common.Constantes.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(MEDICOS)
public class MedicoController {
    private final MedicoService service;
    private final MedicoAssembler assembler;

    @GetMapping
    public Page<MedicoDTO> list(MedicoFilter filter, Pageable pageable) {
        return assembler.toDTO(service.list(filter, pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid MedicoCreate medico) {
        service.save(assembler.toEntity(medico));
    }

    @PutMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid MedicoUpdate medicoUpdate) {
        var medico = service.findById(id);
        assembler.updateEntity(medicoUpdate, medico);
        service.save(medico);
    }

    @PutMapping(ID_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        var medico = service.findById(id);
        service.activate(medico);
    }

    @PutMapping(ID_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        var medico = service.findById(id);
        service.deactivate(medico);
    }

}
