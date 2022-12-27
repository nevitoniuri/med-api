package med.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import med.api.common.PayloadExtractor;
import med.api.domain.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper jsonMapper;
    protected PayloadExtractor payloadExtractor;
    Endereco enderecoMock;

    @BeforeEach
    public void setup() {
        payloadExtractor = new PayloadExtractor(jsonMapper);

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

    protected String toJson(Object o) throws IOException {
        return jsonMapper.writeValueAsString(o);
    }
}
