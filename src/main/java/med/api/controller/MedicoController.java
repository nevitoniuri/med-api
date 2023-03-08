package med.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.api.domain.filter.MedicoFilter;
import med.api.domain.request.MedicoCreate;
import med.api.domain.request.MedicoUpdate;
import med.api.domain.response.MedicoResponse;
import med.api.service.MedicoService;
import med.api.service.mapper.MedicoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static med.api.common.Constantes.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(MEDICOS)
public class MedicoController {
    private final MedicoService service;
    private final MedicoMapper mapper;

    @GetMapping
    public Page<MedicoResponse> list(MedicoFilter filter, Pageable pageable) {
        return service.list(filter, pageable).map(mapper::toDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //TODO: Implementar CreatedResource, para retornar o Location do recurso criado
    public void create(@RequestBody @Valid MedicoCreate medico) {
        service.save(mapper.toEntity(medico));
    }

    @PutMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid MedicoUpdate medicoUpdate) {
        var medico = service.findById(id);
        mapper.updateEntity(medicoUpdate, medico);
        service.save(medico);
    }

    @PutMapping(ID_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        service.activate(service.findById(id));
    }

    @PutMapping(ID_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        service.deactivate(service.findById(id));
    }

}
