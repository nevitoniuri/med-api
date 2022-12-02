create table pacientes
(
    id            bigint       not null,
    nome          varchar(100) not null,
    cpf           varchar(11)   not null,
    email         varchar(100) not null,
    telefone      varchar(11)  not null,
    logradouro    varchar(100) not null,
    numero        varchar(20)  not null,
    complemento   varchar(100),
    bairro        varchar(100) not null,
    cidade        varchar(100) not null,
    uf            varchar(2)   not null,
    cep           varchar(8)   not null,

    constraint pacientes_pk primary key (id),
    constraint pacientes_unique_cpf unique (cpf),
    constraint pacientes_unique_email unique (email),
    constraint pacientes_unique_telefone unique (telefone)
);

create sequence if not exists pacientes_id_seq;

alter table pacientes
    alter column id set default nextval('pacientes_id_seq');