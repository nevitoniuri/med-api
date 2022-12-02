package med.voll.api.controller;

import lombok.RequiredArgsConstructor;
import med.voll.api.model.assembler.PacienteAssembler;
import med.voll.api.model.dto.PacienteDTO;
import med.voll.api.service.PacienteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("pacientes")
public class PacienteController {

    private final PacienteService service;
    private final PacienteAssembler assembler;

    @GetMapping("{id}")
    public PacienteDTO findById(@PathVariable Long id) {
        return assembler.toDTO(service.findById(id));
    }
    @PostMapping
    public void cadastrar(@RequestBody PacienteDTO paciente) {
        service.cadastrar(paciente);
    }

}
