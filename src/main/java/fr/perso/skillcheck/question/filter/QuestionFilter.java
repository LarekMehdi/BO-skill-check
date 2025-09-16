package fr.perso.skillcheck.question.filter;

import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.UtilEntity;

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
    
}
