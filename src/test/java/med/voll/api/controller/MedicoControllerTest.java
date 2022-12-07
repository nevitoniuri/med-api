package med.voll.api.controller;

import lombok.SneakyThrows;
import med.voll.api.controller.request.MedicoCreate;
import med.voll.api.controller.request.MedicoUpdate;
import med.voll.api.controller.response.MedicoDTO;
import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static med.voll.api.common.ControllerURIs.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"classpath:sql/limpar-tabelas.sql",
        "classpath:sql/medicos-inserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MedicoControllerTest extends AbstractControllerTest {

    @Autowired
    private MedicoRepository repository;
    Endereco enderecoMock;

    @BeforeEach
    void setUp() {
        enderecoMock = Endereco.builder()
                .logradouro("Rua das Flores")
                .numero("123")
                .complemento("Apto 101")
                .bairro("Centro")
                .cidade("Fortaleza")
                .uf("CE")
                .cep("60000000")
                .build();
    }

    @Test
    @Order(1)
    @SneakyThrows
    @DisplayName("Deve buscar medico por id")
    void deveBuscarMedicoPorId() {
        var id = repository.findAll().stream().filter(m -> m.getNome().equals("Maria Silva")).iterator().next().getId();
        var request = get(MEDICOS + "/" + id).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(payloadExtractor)
                .andExpect(status().isOk())
                .andReturn();
        var medico = payloadExtractor.as(MedicoDTO.class);
        assertEquals("Maria Silva", medico.nome());
    }

    @Test
    @Order(2)
    @SneakyThrows
    @DisplayName("Deve listar medicos ativos, ordenados por nome")
    void deveListarMedicosAtivos() {
        var request = get(MEDICOS).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(payloadExtractor)
                .andExpect(status().isOk())
                .andReturn();
        var medicos = payloadExtractor.asListOf(MedicoDTO.class, true);
        assertEquals(3, medicos.size());
    }

    @Test
    @Order(3)
    @SneakyThrows
    @DisplayName("Deve criar um novo medico")
    public void deveCriarNovoMedico() {
        var medicoCreate = MedicoCreate.builder()
                .nome("Cláudio Nelson Rezende")
                .crm("788401")
                .email("claudio.rezende@voll.med")
                .telefone("85983475185")
                .especialidade(Especialidade.ORTOPEDIA)
                .endereco(enderecoMock)
                .build();

        var request = post(MEDICOS)
                .content(json(medicoCreate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        Medico medicoCriado = repository.findAll().stream().filter(m -> m.getCrm().equals("788401")).iterator().next();
        assertNotNull(medicoCriado);
        assertEquals("Cláudio Nelson Rezende", medicoCriado.getNome());
    }

    @Test
    @Order(4)
    @SneakyThrows
    @DisplayName("Deve atualizar um medico")
    void deveAtualizarMedico() {
        var id = repository.findAll().stream().filter(m -> m.getNome().equals("Maria Silva")).iterator().next().getId();
        var medicoUpdate = MedicoUpdate.builder()
                .nome("Maria da Silva")
                .telefone("85983475185")
                .endereco(enderecoMock)
                .build();

        var request = put(MEDICOS + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json(medicoUpdate));

        mockMvc.perform(request)
                .andExpect(status().isNoContent())
                .andReturn();

        var medicoAtualizado = repository.findById(id).stream().iterator().next();
        assertEquals("Maria da Silva", medicoAtualizado.getNome());
        assertEquals(enderecoMock.getLogradouro(), medicoAtualizado.getEndereco().getLogradouro());
    }

    @Test
    @Order(5)
    @SneakyThrows
    @DisplayName("Deve desativar um medico")
    void deveDesativarMedico() {
        var medico = repository.findAll().stream().filter(m -> m.getCrm().equals("152390")).iterator().next();
        assertTrue(medico.isAtivo());
        var request = put(MEDICOS + "/" + medico.getId() + DEACTIVATE).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var medicoDesativado = repository.findById(medico.getId()).stream().iterator().next();
        assertFalse(medicoDesativado.isAtivo());
    }

    @Test
    @Order(6)
    @SneakyThrows
    @DisplayName("Deve ativar um medico")
    void deveAtivarMedico() {
        var medico = repository.findAll().stream().filter(m -> m.getCrm().equals("152391")).iterator().next();
        assertFalse(medico.isAtivo());
        var request = put(MEDICOS + "/" + medico.getId() + ACTIVATE).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var medicoAtivado = repository.findById(medico.getId()).stream().iterator().next();
        assertTrue(medicoAtivado.isAtivo());
    }
}
