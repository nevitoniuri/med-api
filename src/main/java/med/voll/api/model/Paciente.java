package med.voll.api.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.model.Endereco;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@Entity @Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    @Embedded
    private Endereco endereco;
}
