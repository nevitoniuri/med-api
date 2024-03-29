package med.api.controller;

import lombok.SneakyThrows;
import med.api.common.Constantes;
import med.api.domain.request.PacienteCreate;
import med.api.domain.request.PacienteUpdate;
import med.api.domain.response.PacienteResponse;
import med.api.repository.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"classpath:sql/limpar-tabelas.sql",
        "classpath:sql/pacientes-inserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PacienteControllerTest extends AbstractControllerTest {

    @Autowired
    private PacienteRepository repository;

    @Test
    @Order(1)
    @SneakyThrows
    @DisplayName("Deve buscar paciente por id")
    void deveBuscarPacientePorId() {
        String cpf = "82303783399";
        var id = repository.findAll().stream().filter(p -> p.getCpf().equals(cpf)).iterator().next().getId();

        var request = MockMvcRequestBuilders.get(Constantes.PACIENTES)
                .param(Constantes.ID_PARAM, String.valueOf(id))
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andDo(payloadExtractor)
                .andExpect(status().isOk())
                .andReturn();

        var paciente = payloadExtractor.asListOf(PacienteResponse.class, true).iterator().next();
        assertEquals(id, paciente.id());
        assertEquals(cpf, paciente.cpf());
    }

    @Test
    @Order(2)
    @SneakyThrows
    @DisplayName("Deve listar os pacientes ativos")
    void deveListarPacientesAtivos() {
        var request = MockMvcRequestBuilders.get(Constantes.PACIENTES).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andDo(payloadExtractor)
                .andExpect(status().isOk())
                .andReturn();

        var pacientes = payloadExtractor.asListOf(PacienteResponse.class, true);

        assertEquals(3, pacientes.size());
        pacientes.forEach(p -> assertTrue(p.ativo()));
    }

    @Test
    @Order(3)
    @SneakyThrows
    @DisplayName("Deve criar um novo paciente")
    void deveCriarPaciente() {
        var pacienteCreate = PacienteCreate.builder()
                .nome("Otávio Breno Danilo Moreira")
                .cpf("60408873388")
                .email("otavio.moreira@voll.med")
                .telefone("11999999999")
                .endereco(enderecoMock)
                .build();

        var request = MockMvcRequestBuilders.post(Constantes.PACIENTES)
                .content(toJson(pacienteCreate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        var paciente = repository.findAll().stream().filter(p -> p.getCpf().equals(pacienteCreate.cpf())).iterator().next();

        assertEquals(pacienteCreate.nome(), paciente.getNome());
        assertEquals(pacienteCreate.cpf(), paciente.getCpf());
        assertEquals(pacienteCreate.email(), paciente.getEmail());
        assertEquals(pacienteCreate.telefone(), paciente.getTelefone());
        assertEquals(pacienteCreate.endereco().getLogradouro(), paciente.getEndereco().getLogradouro());
    }

    @Test
    @Order(4)
    @SneakyThrows
    @DisplayName("Deve atualizar um paciente")
    void deveAtualizarPaciente() {
        var id = repository.findAll().stream().filter(p -> p.getCpf().equals("13241995399")).iterator().next().getId();
        var pacienteUpdate = PacienteUpdate.builder()
                .endereco(enderecoMock)
                .telefone("11999999999")
                .build();

        var request = put(Constantes.PACIENTES + "/" + id)
                .content(toJson(pacienteUpdate))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNoContent())
                .andReturn();

        var paciente = repository.findById(id).stream().iterator().next();
        assertEquals(pacienteUpdate.telefone(), paciente.getTelefone());
        assertEquals(pacienteUpdate.endereco().getLogradouro(), paciente.getEndereco().getLogradouro());
    }

    @Test
    @Order(5)
    @SneakyThrows
    @DisplayName("Deve ativar um paciente")
    void deveAtivarPaciente() {
        var paciente = repository.findAll().stream().filter(p -> p.getCpf().equals("73093165334")).iterator().next();
        assertFalse(paciente.isAtivo());

        var request = put(Constantes.PACIENTES + "/" + paciente.getId() + Constantes.ACTIVATE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNoContent())
                .andReturn();

        var pacienteAtivado = repository.findById(paciente.getId()).stream().iterator().next();
        assertTrue(pacienteAtivado.isAtivo());
    }

    @Test
    @Order(6)
    @SneakyThrows
    @DisplayName("Deve desativar um paciente")
    void deveDesativarPaciente() {
        var paciente = repository.findAll().stream().filter(p -> p.getCpf().equals("13241995399")).iterator().next();
        assertTrue(paciente.isAtivo());

        var request = put(Constantes.PACIENTES + "/" + paciente.getId() + Constantes.DEACTIVATE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNoContent())
                .andReturn();

        var pacienteDesativado = repository.findById(paciente.getId()).stream().iterator().next();
        assertFalse(pacienteDesativado.isAtivo());
    }
}
