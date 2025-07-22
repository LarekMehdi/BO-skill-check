package fr.perso.skillcheck.answer.dto;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnswerDto {
    
    private Long id;
    private Long questionId;

    @NotBlank(message = "Le content ne doit pas être vide")
    private String content;

    @NotNull(message = "Le champ isCorrect est requis")
    private Boolean isCorrect;

    public AnswerDto() {}

    public AnswerDto(Long id, Long questionId, String content, Boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.isCorrect = isCorrect;
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

    /** IS CORRECT **/

    public Boolean getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean hasIsCorrect() {
        return !UtilEntity.isEmpty(this.isCorrect);
    }

    public boolean isCorrectTrue() {
        return this.hasIsCorrect() && Boolean.TRUE.equals(this.isCorrect);
    }
}
