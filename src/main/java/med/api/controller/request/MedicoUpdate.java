package med.api.controller.request;

import jakarta.validation.Valid;
import lombok.Builder;
import med.api.model.Endereco;

@Builder
public record MedicoUpdate(
        String nome,
        String telefone,
        @Valid
        Endereco endereco) {
}
