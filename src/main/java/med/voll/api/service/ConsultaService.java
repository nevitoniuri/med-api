package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.model.Consulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository repository;
    private final MedicoService medicoService;

    public void create(Consulta consulta) {


    }
}
