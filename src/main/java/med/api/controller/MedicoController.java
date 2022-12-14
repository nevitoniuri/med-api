package med.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.api.common.Constantes;
import med.api.controller.filter.MedicoFilter;
import med.api.controller.request.MedicoCreate;
import med.api.controller.request.MedicoUpdate;
import med.api.controller.response.MedicoDTO;
import med.api.service.MedicoService;
import med.api.service.assembler.MedicoAssembler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constantes.MEDICOS)
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

    @PutMapping(Constantes.ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid MedicoUpdate medicoUpdate) {
        var medico = service.findById(id);
        assembler.updateEntity(medicoUpdate, medico);
        service.save(medico);
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
