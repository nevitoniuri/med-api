package med.voll.api.controller;

import lombok.SneakyThrows;
import med.voll.api.controller.response.MedicoDTO;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.service.MedicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"classpath:sql/limpar-tabelas.sql",
        "classpath:sql/medicos-inserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = "classpath:sql/medicos-inserts.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MedicoControllerTest extends AbstractControllerTest {

    @Autowired
    private MedicoService service;
    @Autowired
    private MedicoRepository repository;

    @Test
    @Order(1)
    @SneakyThrows
    @DisplayName("Deve listar medicos ativos, ordenados por nome")
    public void deveListarMedicosAtivos() {
        var request = get("/medicos").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(payloadExtractor)
                .andExpect(status().isOk())
                .andReturn();
        var medicos = payloadExtractor.asListOf(MedicoDTO.class, true);
        assertEquals(3, medicos.size());
    }

    @Test
    @Order(2)
    @SneakyThrows
    @DisplayName("Deve buscar medico por id")
    public void deveBuscarMedicoPorId() {
        var id = repository.findAll().stream().filter(m -> m.getNome().equals("Maria Silva")).iterator().next().getId();
        var request = get("/medicos/" + id).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(payloadExtractor)
                .andExpect(status().isOk())
                .andReturn();
        var medico = payloadExtractor.as(MedicoDTO.class);
        assertEquals("Maria Silva", medico.nome());
    }

    
}
