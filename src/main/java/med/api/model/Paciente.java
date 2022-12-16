package med.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "ativo", nullable = false)
    private boolean ativo;
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "telefone", nullable = false, unique = true)
    private String telefone;
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
