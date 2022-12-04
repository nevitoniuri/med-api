package med.voll.api.model;

import jakarta.persistence.PrePersist;
import lombok.Getter;

public class Cadastro {

    @Getter
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
