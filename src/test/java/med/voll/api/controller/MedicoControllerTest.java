package med.voll.api.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(classes = {MedicoController.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @SneakyThrows
    @DisplayName("Deve cadastrar um novo médico")
    public void deveCriarMedico() {
        var request = MockMvcRequestBuilders
                .get("/medicos")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request);
    }
}
