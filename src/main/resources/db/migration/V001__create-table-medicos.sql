create table medicos
(
    id            bigint       not null,
    nome          varchar(100) not null,
    crm           varchar(6)   not null,
    email         varchar(100) not null,
    telefone      varchar(11)  not null,
    especialidade varchar(100) not null,
    logradouro    varchar(100) not null,
    numero        varchar(20)  not null,
    complemento   varchar(100),
    bairro        varchar(100) not null,
    cidade        varchar(100) not null,
    uf            varchar(2)   not null,
    cep           varchar(8)   not null,

    constraint medicos_pk primary key (id),
    constraint medicos_unique_crm unique (crm),
    constraint medicos_unique_email unique (email),
    constraint medicos_unique_telefone unique (telefone)
);

create sequence if not exists medicos_id_seq;

alter table medicos
    alter column id set default nextval('medicos_id_seq');