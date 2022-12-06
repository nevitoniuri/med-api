create sequence if not exists consultas_id_seq;

create table consultas
(
    id                bigint    not null default nextval('consultas_id_seq'),
    medico_id         bigint    not null,
    paciente_id       bigint    not null,
    data_hora         timestamp not null,
    data_hora_criacao timestamp not null,

    constraint consultas_pk primary key (id),
    constraint consultas_medicos_fk foreign key (medico_id) references medicos (id),
    constraint consultas_pacientes_fk foreign key (paciente_id) references pacientes (id)
);