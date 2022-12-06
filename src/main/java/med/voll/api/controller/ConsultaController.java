package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.model.assembler.ConsultaAssembler;
import med.voll.api.model.request.ConsultaCreate;
import med.voll.api.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("consultas")
public class ConsultaController {

    private final ConsultaService service;
    private final ConsultaAssembler assembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ConsultaCreate consulta) {
        service.create(assembler.toEntity(consulta));
    }
}
