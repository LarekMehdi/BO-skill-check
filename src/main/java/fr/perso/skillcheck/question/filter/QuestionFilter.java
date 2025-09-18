package fr.perso.skillcheck.question.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.questionHasTag.QuestionHasTag;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class QuestionFilter extends GenericFilter{

    private Difficulty          difficulty;

    private Long                tagId;

    public QuestionFilter() {
        super();
    }

    public QuestionFilter(Difficulty difficulty, Long tagId) {
        super();
        this.difficulty = difficulty;
        this.tagId = tagId;
    }

    /** DIFFICULTY **/

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean hasDifficulty() {
        return !UtilEntity.isEmpty(this.difficulty);
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

    public Specification<Question> toSpecification() {
        //root => colonne
        //query => requete complete
        //cb => criteriaBuilder
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.hasDifficulty()) {
                predicates.add(cb.equal(root.get("difficulty"), this.getDifficulty()));
            }

            if (this.hasTagId() && query != null) {
                Subquery<Long> sq = query.subquery(Long.class);
                Root<QuestionHasTag> qht = sq.from(QuestionHasTag.class);
                sq.select(qht.get("question").get("id"))
                .where(cb.equal(qht.get("tag").get("id"), this.getTagId()));
                predicates.add(root.get("id").in(sq));
            }

            //exemple
            // if (this.hasTitle()) {
            //     predicates.add(cb.like(cb.lower(root.get("title")), "%" + this.getTitle().toLowerCase() + "%"));
            // }

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    
}
