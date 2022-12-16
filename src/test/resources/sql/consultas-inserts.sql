-- INSERT MEDICOS
insert into medicos (id, ativo, nome, crm, email, especialidade, telefone, logradouro, numero, complemento, bairro,
                     cidade, uf, cep)
values (nextval('medicos_id_seq'), true, 'Edmundo Soares', '152388', 'edmundo.soares@voll.med', 'ORTOPEDIA',
        '85973782280', 'Vila Ayra', '999', '', 'Fátima', 'Fortaleza', 'CE', '60040320');

insert into medicos (id, ativo, nome, crm, email, especialidade, telefone, logradouro, numero, complemento, bairro,
                     cidade, uf, cep)
values (nextval('medicos_id_seq'), false, 'José da Silva', '152391', 'jose.silva@voll.med', 'CARDIOLOGIA',
        '85973782283', 'Rua Tenente Amauri Pio', '350', '', 'Meireles', 'Fortaleza', 'CE', '60160090');

-- INSERT PACIENTES
insert into pacientes (id, ativo, nome, cpf, email, telefone, logradouro, numero, complemento, bairro,
                       cidade, uf, cep)
values (nextval('pacientes_id_seq'), false, 'Brenda Jéssica Viana', '73093165334', 'brenda_jessica_viana@grupoitamaraty.com.br',
        '85985597614', 'Rua Saldaria', '326', '', 'Barra do Ceará', 'Fortaleza', 'CE', '60332710');

insert into pacientes (id, ativo, nome, cpf, email, telefone, logradouro, numero, complemento, bairro,
                       cidade, uf, cep)
values (nextval('pacientes_id_seq'), true, 'Murilo Joaquim Freitas', '13241995399', 'murilojoaquimfreitas@metalplasma.com.br',
        '85998725766', 'Vila Rodrigues', '162', '', 'São João do Tauape', 'Fortaleza', 'CE', '60125210');

-- INSERT CONSULTAS
insert into consultas (id, status, medico_id, paciente_id, data_hora, data_hora_criacao, motivo_cancelamento)
values (nextval('consultas_id_seq'), 'AGENDADA',
        (select id from medicos where nome = 'Edmundo Soares'),
        (select id from pacientes where cpf = 'Brenda Jéssica Viana'),
        current_date + interval '1 day', now(), null);