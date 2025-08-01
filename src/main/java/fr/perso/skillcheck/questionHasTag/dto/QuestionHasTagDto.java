package fr.perso.skillcheck.questionHasTag.dto;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotNull;

public class QuestionHasTagDto {

    private Long        id;
    
    @NotNull(message = "Le champ questionId est requis")
    private Long        questionId;

    @NotNull(message = "Le champ tagId est requis")
    private Long         tagId;

    public QuestionHasTagDto() {}

    public QuestionHasTagDto(Long id, Long questionId, Long tagId) {
        this.id = id;
        this.questionId = questionId;
        this.tagId = tagId;
    }

    public QuestionHasTagDto(Long questionId, Long tagId) {
        this.questionId = questionId;
        this.tagId = tagId;
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

    /** QUESTION ID **/

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public boolean hasQuestionId() {
        return !UtilEntity.isEmpty(this.questionId);
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
}
