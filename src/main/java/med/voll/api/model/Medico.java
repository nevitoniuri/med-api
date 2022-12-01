package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.model.Endereco;
import med.voll.api.model.Especialidade;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
@Entity @Table(name = "medicos")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crm;
    private String email;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

}
