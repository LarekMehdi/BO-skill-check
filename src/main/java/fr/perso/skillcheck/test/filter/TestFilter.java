package fr.perso.skillcheck.test.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import fr.perso.skillcheck.constants.SortOrder;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.testHasTag.TestHasTag;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class TestFilter extends GenericFilter{

    private String          title;

    private Long            tagId;
    
    public TestFilter() {
        super();
    }

    public TestFilter(Integer limit, Integer offset, String sortBy, SortOrder sortOrder) {
        super(limit, offset, sortBy, sortOrder);
    }

    public TestFilter(Integer limit, Integer offset) {
        super(limit, offset);
    }

    public TestFilter(String title, Long tagId) {
        super();
        this.title = title;
        this.tagId = tagId;
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

    /** TAG ID **/

    public Long getTagId() {
        return this.tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public boolean hasTagId() {
        return !UtilEntity.isEmpty(this.tagId);
    }

    /** METHODS **/

    public Specification<Test> toSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.hasTitle()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + this.getTitle() + "%"));
            }

            if (this.hasTagId() && query != null) {
                Subquery<Long> sq = query.subquery(Long.class);
                Root<TestHasTag> qht = sq.from(TestHasTag.class);
                sq.select(qht.get("test").get("id"))
                .where(cb.equal(qht.get("tag").get("id"), this.getTagId()));
                predicates.add(root.get("id").in(sq));
            }

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
