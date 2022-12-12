package med.voll.api.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.controller.assembler.ConsultaAssembler;
import med.voll.api.controller.request.ConsultaCreate;
import med.voll.api.model.MotivoCancelamento;
import med.voll.api.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static med.voll.api.common.ControllerURIs.CONSULTAS;
import static med.voll.api.common.ControllerURIs.ID;
import static med.voll.api.common.Utils.handleDataHora;

@RestController
@RequiredArgsConstructor
@RequestMapping(CONSULTAS)
public class ConsultaController {

    private final ConsultaService service;
    private final ConsultaAssembler assembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ConsultaCreate consulta) {
        service.save(assembler.toEntity(consulta));
    }

    @PutMapping(ID)
    public void reagendar(@PathVariable Long id, @RequestParam @JsonFormat(pattern = "yyyy-MM-dd HH:mm") String dataHora) {
        var consulta = service.findById(id);
        service.reagendar(consulta, handleDataHora(dataHora));
    }

    @PutMapping("{id}/cancelar")
    public void cancelar(@PathVariable Long id, @RequestParam MotivoCancelamento motivo) {
        var consulta = service.findById(id);
        service.cancelar(consulta, motivo);
    }
}
