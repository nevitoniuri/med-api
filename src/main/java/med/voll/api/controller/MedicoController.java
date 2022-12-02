package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.model.assembler.MedicoAssembler;
import med.voll.api.model.dto.MedicoDTO;
import med.voll.api.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("all")
    public List<MedicoDTO> listAll() {
        return assembler.toDTO(service.listAll());
    }

    @GetMapping
    public Page<MedicoDTO> listPaginated(@PageableDefault(sort = "nome") Pageable pageable) {
        return assembler.toDTO(service.listPaginated(pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid MedicoDTO medico) {
        service.create(assembler.toEntity(medico));
    }

}
