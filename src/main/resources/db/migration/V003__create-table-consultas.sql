CREATE TABLE consultas
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    status              VARCHAR(32)                         NOT NULL,
    medico_id           BIGINT                              NOT NULL,
    paciente_id         BIGINT                              NOT NULL,
    data_hora           TIMESTAMP                           NOT NULL,
    data_hora_criacao   TIMESTAMP                           NOT NULL,
    motivo_cancelamento VARCHAR(128),

    CONSTRAINT consultas_pkey PRIMARY KEY (id),
    CONSTRAINT consultas_medicos_fkey FOREIGN KEY (medico_id) REFERENCES medicos (id),
    CONSTRAINT consultas_pacientes_fkey FOREIGN KEY (paciente_id) REFERENCES pacientes (id)
);