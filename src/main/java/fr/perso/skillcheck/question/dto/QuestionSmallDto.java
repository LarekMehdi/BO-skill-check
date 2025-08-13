package fr.perso.skillcheck.question.dto;

import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.utils.UtilEntity;

public class QuestionSmallDto {
    
    private Long        id;
    private String      content;
    private String      code;
    private Difficulty  difficulty;

    public QuestionSmallDto() {}

    public QuestionSmallDto(Long id, String content, String code, Difficulty difficulty) {
        this.id = id;
        this.content = content;
        this.code = code;
        this.difficulty = difficulty;
    }

    public QuestionSmallDto(String content, Difficulty difficulty) {
        this.content = content;
        this.difficulty = difficulty;
    }

    public QuestionSmallDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
        this.code = question.getCode();
        this.difficulty = question.getDifficulty();
    }

    /** ID **/

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasId() {
        return !UtilEntity.isEmpty(this.id);
    }

    /** CONTENT **/

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean hasContent() {
        return !UtilEntity.isEmpty(this.content);
    }

    /** CODE **/

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean hasCode() {
        return !UtilEntity.isEmpty(this.code);
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
