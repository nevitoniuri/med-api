package med.voll.api.controller;

import lombok.RequiredArgsConstructor;
import med.voll.api.model.assembler.ConsultaAssembler;
import med.voll.api.model.request.ConsultaCreate;
import med.voll.api.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;
    private final ConsultaAssembler assembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(ConsultaCreate consulta) {
        service.create(assembler.toEntity(consulta));
    }
}
