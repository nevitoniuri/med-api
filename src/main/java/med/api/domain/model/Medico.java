package med.api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import med.api.domain.enums.Especialidade;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "ativo", nullable = false)
    private boolean ativo;
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;
    @Column(name = "crm", nullable = false, unique = true)
    private String crm;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "telefone", nullable = false, unique = true)
    private String telefone;
    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade", nullable = false)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

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
