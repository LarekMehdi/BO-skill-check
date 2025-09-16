package fr.perso.skillcheck.question.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.criteria.Predicate;

public class QuestionFilter extends GenericFilter{

    private Difficulty          difficulty;

    public QuestionFilter() {
        super();
    }

    public QuestionFilter(Difficulty difficulty) {
        super();
        this.difficulty = difficulty;
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

            //exemple
            // if (this.hasTitle()) {
            //     predicates.add(cb.like(cb.lower(root.get("title")), "%" + this.getTitle().toLowerCase() + "%"));
            // }

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    
}
