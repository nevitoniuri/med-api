package med.voll.api.controller;

import lombok.RequiredArgsConstructor;
import med.voll.api.model.dto.PacienteDTO;
import med.voll.api.service.PacienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("pacientes")
public class PacienteController {

    private final PacienteService service;

    @PostMapping
    public void cadastrar(@RequestBody PacienteDTO paciente) {
        service.cadastrar(paciente);
    }

}
