package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.model.assembler.MedicoAssembler;
import med.voll.api.model.dto.MedicoDTO;
import med.voll.api.model.request.MedicoCreate;
import med.voll.api.model.request.MedicoUpdate;
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
    public void update(@PathVariable Long id, @RequestBody @Valid MedicoUpdate medicoUpdate) {
        var medico = service.findById(id);
        assembler.updateEntity(medicoUpdate, medico);
        service.save(medico);
    }

}
