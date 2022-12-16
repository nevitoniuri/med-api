package med.api.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constantes {

    //ENTIDADES
    public static final String ID_PARAM = "id";
    public static final String ATIVO = "ativo";
    public static final String NOME = "nome";
    public static final String CRM = "crm";
    public static final String CPF = "cpf";
    public static final String EMAIL = "email";
    public static final String TELEFONE = "telefone";
    public static final String ESPECIALIDADE = "especialidade";
    public static final String ENDERECO = "endereco";

    //CONTROLLERS
    public static final String MEDICOS = "/medicos";
    public static final String PACIENTES = "/pacientes";
    public static final String CONSULTAS = "/consultas";
    public static final String ID = "{id}";
    public static final String ACTIVATE = "/activate";
    public static final String DEACTIVATE = "/deactivate";
    public static final String ID_ACTIVATE = ID + ACTIVATE;
    public static final String ID_DEACTIVATE = ID + DEACTIVATE;
}
