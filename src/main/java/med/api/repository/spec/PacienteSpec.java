package med.api.repository.spec;

import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import med.api.common.Status;
import med.api.domain.filter.PacienteFilter;
import med.api.domain.model.Paciente;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static med.api.common.Constantes.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PacienteSpec {

    public static Specification<Paciente> exists(Paciente paciente) {
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.or(
                    builder.equal(builder.lower(root.get(NOME)), paciente.getNome().toLowerCase()),
                    builder.equal(root.get(CPF), paciente.getCpf()),
                    builder.equal(builder.lower(root.get(EMAIL)), paciente.getEmail().toLowerCase()),
                    builder.equal(root.get(TELEFONE), paciente.getTelefone())
            ));

            if (Objects.nonNull(paciente.getId())) {
                predicates.add(builder.notEqual(root.get(ID_PARAM), paciente.getId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }

    //TODO: Como Refatorar?
    public static Specification<Paciente> filter(PacienteFilter filter) {
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(filter.id())) {
                predicates.add(builder.equal(root.get(ID_PARAM), filter.id()));
            }
            if (Objects.nonNull(filter.nome())) {
                predicates.add(builder.like(builder.lower(root.get(NOME)), "%" + filter.nome().toLowerCase() + "%"));
            }
            if (Objects.nonNull(filter.cpf())) {
                predicates.add(builder.equal(root.get(CPF), filter.cpf()));
            }
            if (Objects.nonNull(filter.email())) {
                predicates.add(builder.like(builder.lower(root.get(EMAIL)), "%" + filter.email().toLowerCase() + "%"));
            }
            if (Objects.nonNull(filter.telefone())) {
                predicates.add(builder.equal(root.get(TELEFONE), filter.telefone()));
            }

            if (Objects.isNull(filter.status()) || filter.status().isEmpty()) {
                predicates.add(builder.equal(root.get(ATIVO), true));
            } else {
                var listaStatus = Status.getListaStatus(filter.status());
                predicates.add(root.get(ATIVO).in(listaStatus));
            }

            query.orderBy(builder.asc(root.get(NOME)));

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
