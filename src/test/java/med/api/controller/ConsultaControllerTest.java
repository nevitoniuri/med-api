package med.api.controller;

import med.api.controller.request.ConsultaCreate;
import med.api.service.ConsultaService;
import med.api.service.MedicoService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"classpath:sql/limpar-tabelas.sql",
        "classpath:sql/consultas-inserts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ConsultaControllerTest extends AbstractControllerTest {

    @Autowired
    private ConsultaService service;

    @Mock
    private MedicoService medicoService;

    @Test
    @Order(1)
    void deveCriarConsulta() {

        var consulta = ConsultaCreate.builder()
                .dataHora("2021-01-01T10:00:00")
                .medicoId(1L)
                .pacienteId(1L)
                .build();
    }
}
