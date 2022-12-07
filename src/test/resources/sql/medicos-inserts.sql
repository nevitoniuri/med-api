insert into medicos (id, ativo, nome, crm, email, especialidade, telefone, logradouro, numero, complemento, bairro,
                     cidade, uf, cep)
values (nextval('medicos_id_seq'), true, 'Maria Silva', '152390', 'maria.silva@voll.med', 'GINECOLOGIA',
        '85973782282', 'Rua Ubaitabá', '1025', '', 'Edson Queiroz', 'Fortaleza', 'CE', '60812250');

insert into medicos (id, ativo, nome, crm, email, especialidade, telefone, logradouro, numero, complemento, bairro,
                     cidade, uf, cep)
values (nextval('medicos_id_seq'), true, 'João Oliveira', '152389', 'joao.oliveira@voll.med', 'DERMATOLOGIA',
        '85973782281', 'Rua José Abílio', '128', '', 'Granja Portugal', 'Fortaleza', 'CE', '60541055');

insert into medicos (id, ativo, nome, crm, email, especialidade, telefone, logradouro, numero, complemento, bairro,
                     cidade, uf, cep)
values (nextval('medicos_id_seq'), true, 'Edmundo Soares', '152388', 'edmundo.soares@voll.med', 'ORTOPEDIA',
        '85973782280', 'Vila Ayra', '999', '', 'Fátima', 'Fortaleza', 'CE', '60040320');

insert into medicos (id, ativo, nome, crm, email, especialidade, telefone, logradouro, numero, complemento, bairro,
                     cidade, uf, cep)
values (nextval('medicos_id_seq'), false, 'José da Silva', '152391', 'jose.silva@voll.med', 'CARDIOLOGIA',
        '85973782283', 'Rua Tenente Amauri Pio', '350', '', 'Meireles', 'Fortaleza', 'CE', '60160090');