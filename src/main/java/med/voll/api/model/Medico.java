package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String nome;
    @Setter
    private String crm;
    @Setter
    private String email;
    @Setter
    private String telefone;
    @Setter
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Setter
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    @PrePersist
    public void prePersist() {
        this.ativo = Boolean.TRUE;
    }

    public void activate() {
        this.ativo = Boolean.TRUE;
    }

    public void deactivate() {
        this.ativo = Boolean.FALSE;
    }

}
