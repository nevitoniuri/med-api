CREATE TABLE medicos
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
    ativo         BOOLEAN DEFAULT TRUE                NOT NULL,
    nome          VARCHAR(128)                        NOT NULL,
    crm           VARCHAR(6)                          NOT NULL,
    email         VARCHAR(128)                        NOT NULL,
    telefone      VARCHAR(11)                         NOT NULL,
    especialidade VARCHAR(128)                        NOT NULL,
    logradouro    VARCHAR(128)                        NOT NULL,
    numero        VARCHAR(20)                         NOT NULL,
    complemento   VARCHAR(128),
    bairro        VARCHAR(128)                        NOT NULL,
    cidade        VARCHAR(128)                        NOT NULL,
    uf            VARCHAR(2)                          NOT NULL,
    cep           VARCHAR(8)                          NOT NULL,

    CONSTRAINT medicos_nome_key UNIQUE (nome),
    CONSTRAINT medicos_crm_key UNIQUE (crm),
    CONSTRAINT medicos_email_key UNIQUE (email),
    CONSTRAINT medicos_telefone_key UNIQUE (telefone)
);