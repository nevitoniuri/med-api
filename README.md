# med-api

Uma API REST que simula uma clínica médica, sendo possível agendar e gerenciar consultas

## Tecnologias utilizadas
- Java 17
- Spring Boot 3
- Spring Data JPA
- Flyway Migration
- PostgreSQL Database para DEV e H2 Database para TEST
- Consultas das entidades com paginação e filtragem, por Specification e Criteria API
- Orientação a Interfaces
- Spring DevTools
- Bean Validation nos endpoints
- Testes de integração com Junit e Mockito
- Maven
- Postman
- Lombok
- Swagger para documentação da API (http://localhost:8080/swagger-ui.html)

## Funcionalidades
- Pacientes (Cadastros, updates, listagem)
- Médicos (Cadastros, updates, listagem)
- Consultas (Agendamento, Reagendamento, Cancelamento, Motivo)

## Regras de Negócio
- O horário de funcionamento da clínica é de segunda a sábado, das 07:00 às 19:00;
- As consultas tem duração fixa de 1 hora;
- As consultas devem ser agendadas com antecedência mínima de 30 minutos;
- Não permitir o agendamento de consultas com pacientes inativos no sistema;
- Não permitir o agendamento de consultas com médicos inativos no sistema;
- Não permitir o agendamento de mais de uma consulta no mesmo dia para um mesmo paciente;
- Não permitir o agendamento de uma consulta com um médico que já possui outra consulta agendada na mesma data/hora;
- Não permitir o agendamento de uma consulta em um horário que já esteja ocupado;
- A escolha do médico é opcional, sendo que nesse caso o sistema deve escolher aleatoriamente algum médico disponível na data/hora preenchida.
- É obrigatório informar o motivo do cancelamento da consulta, dentre as opções: paciente desistiu, médico cancelou ou outros;
- Uma consulta somente poderá ser cancelada com antecedência mínima de 24 horas.