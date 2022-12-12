package med.voll.api.repository.spec;

import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import med.voll.api.model.Medico;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MedicoSpec {

    public static Specification<Medico> exists(Medico medico) {
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.or(
                    builder.equal(builder.lower(root.get("nome")), medico.getNome().toLowerCase()),
                    builder.equal(root.get("crm"), medico.getCrm()),
                    builder.equal(builder.lower(root.get("email")), medico.getEmail().toLowerCase()),
                    builder.equal(root.get("telefone"), medico.getTelefone())
            ));

            if (Objects.nonNull(medico.getId())) {
                predicates.add(builder.notEqual(root.get("id"), medico.getId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
