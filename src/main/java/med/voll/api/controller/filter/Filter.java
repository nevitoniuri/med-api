package med.voll.api.controller.filter;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static med.voll.api.common.Constantes.*;

public class Filter<E, F> {

//    public Specification<E> filter(F filter) {
//        return ((root, query, builder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (Objects.nonNull(filter.id())) {
//                predicates.add(builder.equal(root.get(ID_PARAM), filter.id()));
//            }
//            if (Objects.nonNull(filter.nome())) {
//                predicates.add(builder.like(builder.lower(root.get(NOME)), "%" + filter.nome().toLowerCase() + "%"));
//            }
//            if (Objects.nonNull(filter.cpf())) {
//                predicates.add(builder.equal(root.get(CPF), filter.cpf()));
//            }
//            if (Objects.nonNull(filter.email())) {
//                predicates.add(builder.like(builder.lower(root.get(EMAIL)), "%" + filter.email().toLowerCase() + "%"));
//            }
//            if (Objects.nonNull(filter.telefone())) {
//                predicates.add(builder.equal(root.get(TELEFONE), filter.telefone()));
//            }
//
//            return builder.and(predicates.toArray(new Predicate[0]));
//        });
//    }
}
