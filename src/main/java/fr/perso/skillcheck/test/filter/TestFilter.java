package fr.perso.skillcheck.test.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import fr.perso.skillcheck.constants.SortOrder;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.criteria.Predicate;

public class TestFilter extends GenericFilter{

    private String          title;
    
    public TestFilter() {
        super();
    }

    public TestFilter(Integer limit, Integer offset, String sortBy, SortOrder sortOrder) {
        super(limit, offset, sortBy, sortOrder);
    }

    public TestFilter(Integer limit, Integer offset) {
        super(limit, offset);
    }

    public TestFilter(String title) {
        super();
        this.title = title;
    }

    /** TITLE **/

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasTitle() {
        return !UtilEntity.isEmpty(this.title);
    }

    /** METHODS **/

    public Specification<Test> toSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.hasTitle()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + this.getTitle() + "%"));
            }

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
