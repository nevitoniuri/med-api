package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.model.dto.MedicoDTO;
import med.voll.api.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("medicos")
public class MedicoController {

    private final MedicoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody @Valid MedicoDTO medico) {
        service.cadastrar(medico);
    }
}
