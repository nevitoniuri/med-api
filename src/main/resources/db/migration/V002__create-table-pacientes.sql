CREATE TABLE pacientes
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
    ativo       BOOLEAN DEFAULT TRUE                NOT NULL,
    nome        VARCHAR(128)                        NOT NULL,
    cpf         VARCHAR(11)                         NOT NULL,
    email       VARCHAR(128)                        NOT NULL,
    telefone    VARCHAR(11)                         NOT NULL,
    logradouro  VARCHAR(128)                        NOT NULL,
    numero      VARCHAR(20)                         NOT NULL,
    complemento VARCHAR(128),
    bairro      VARCHAR(128)                        NOT NULL,
    cidade      VARCHAR(128)                        NOT NULL,
    uf          VARCHAR(2)                          NOT NULL,
    cep         VARCHAR(8)                          NOT NULL,

    CONSTRAINT pacientes_nome_key UNIQUE (nome),
    CONSTRAINT pacientes_cpf_key UNIQUE (cpf),
    CONSTRAINT pacientes_email_key UNIQUE (email),
    CONSTRAINT pacientes_telefone_key UNIQUE (telefone)
);