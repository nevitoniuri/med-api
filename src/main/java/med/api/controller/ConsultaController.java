package med.api.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.api.common.Constantes;
import med.api.common.Utils;
import med.api.domain.request.ConsultaCreate;
import med.api.service.ConsultaService;
import med.api.service.mapper.ConsultaMapper;
import med.api.domain.enums.MotivoCancelamento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constantes.CONSULTAS)
public class ConsultaController {

    private final ConsultaService service;
    private final ConsultaMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ConsultaCreate consulta) {
        service.save(mapper.toEntity(consulta));
    }

    @PutMapping(Constantes.ID)
    public void reagendar(@PathVariable Long id, @RequestParam @JsonFormat(pattern = "yyyy-MM-dd HH:mm") String dataHora) {
        service.reagendar(service.findById(id), Utils.handleDataHora(dataHora));
    }

    @PutMapping("{id}/cancelar")
    public void cancelar(@PathVariable Long id, @RequestParam MotivoCancelamento motivo) {
        service.cancelar(service.findById(id), motivo);
    }
}
