package med.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("med-api")
                        .description("API Rest que contém as funcionalidades de CRUD de médicos e de pacientes, além de agendamento e cancelamento de consultas")
                        .contact(new Contact()
                                .name("Backend Developer")
                                .email("iurineviton@gmail.com"))
                );
    }

}
