package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.controller.assembler.ConsultaAssembler;
import med.voll.api.controller.request.ConsultaCreate;
import med.voll.api.controller.request.ConsultaUpdate;
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

    @PutMapping("{id}")
    public void reagendar(@PathVariable Long id, @RequestBody @Valid ConsultaUpdate consultaUpdate) {
        var consulta = service.findById(id);
        assembler.updateEntity(consultaUpdate, consulta);
        service.create(consulta);
    }

    //TODO: Mudar implementação, para evitar 2 métodos com a mesma assinatura
    @PutMapping("{id}/cancelar")
    public void cancelar(@PathVariable Long id, @RequestBody @Valid ConsultaUpdate consultaUpdate) {
        var consulta = service.findById(id);
        assembler.updateEntity(consultaUpdate, consulta);
        service.create(consulta);
    }
}
