package med.api.repository.spec;

import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import med.api.common.Status;
import med.api.controller.filter.MedicoFilter;
import med.api.domain.enums.Especialidade;
import med.api.domain.model.Medico;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static med.api.common.Constantes.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MedicoSpec {

    public static Specification<Medico> exists(Medico medico) {
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.or(
                    builder.equal(builder.lower(root.get(NOME)), medico.getNome().toLowerCase()),
                    builder.equal(root.get(CRM), medico.getCrm()),
                    builder.equal(builder.lower(root.get(EMAIL)), medico.getEmail().toLowerCase()),
                    builder.equal(root.get(TELEFONE), medico.getTelefone())
            ));

            if (Objects.nonNull(medico.getId())) {
                predicates.add(builder.notEqual(root.get(ID_PARAM), medico.getId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }

    //TODO: Como Refatorar?
    public static Specification<Medico> filter(MedicoFilter filter) {
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(filter.id())) {
                predicates.add(builder.equal(root.get(ID_PARAM), filter.id()));
            }
            if (Objects.nonNull(filter.nome())) {
                predicates.add(builder.like(builder.lower(root.get(NOME)), "%" + filter.nome().toLowerCase() + "%"));
            }
            if (Objects.nonNull(filter.crm())) {
                predicates.add(builder.equal(root.get(CRM), filter.crm()));
            }
            if (Objects.nonNull(filter.email())) {
                predicates.add(builder.like(builder.lower(root.get(EMAIL)), "%" + filter.email().toLowerCase() + "%"));
            }
            if (Objects.nonNull(filter.telefone())) {
                predicates.add(builder.equal(root.get(TELEFONE), filter.telefone()));
            }
            if (Objects.nonNull(filter.especialidade())) {
                var listaEspecialidade = filter.especialidade().stream()
                        .map(Especialidade::of)
                        .toList();
                predicates.add(root.get(ESPECIALIDADE).in(listaEspecialidade));
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
