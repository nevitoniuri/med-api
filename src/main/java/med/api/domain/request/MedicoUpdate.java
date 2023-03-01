package med.api.domain.request;

import jakarta.validation.Valid;
import lombok.Builder;
import med.api.domain.model.Endereco;

@Builder
public record MedicoUpdate(
        String nome,
        String telefone,
        @Valid
        Endereco endereco) {
}
