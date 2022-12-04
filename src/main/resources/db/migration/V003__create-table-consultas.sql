create table consultas
(
    id                bigint    not null,
    medico_id         bigint    not null,
    paciente_id       bigint    not null,
    data_hora         timestamp not null,
    data_hora_criacao timestamp not null,

    constraint consultas_pk primary key (id)
);

create sequence if not exists consultas_id_seq;

alter table consultas
    alter column id set default nextval('consultas_id_seq');