package fr.perso.skillcheck.question.dto;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class QuestionDto {

    @NotBlank(message = "Le content ne doit pas être vide")
    private String  content;

    @NotNull(message = "Le champ isMultipleAnswer est requis")
    private Boolean isMultipleAnswer;
    
    private Double successRate;

    @NotNull(message = "Le champ timer est requis")
    @Positive(message = "Le champ timer doit être supérieur à 0")
    private Integer timer;

    public QuestionDto() {}

    public QuestionDto(String content, Boolean isMultipleAnswer, Integer timer) {
        this.content = content;
        this.isMultipleAnswer = isMultipleAnswer;
        this.timer = timer;
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

    /** IS MULTIPLE ANSWER **/

    public Boolean getIsMultipleAnswer() {
        return this.isMultipleAnswer;
    }

    public void setIsMultipleAnswer(Boolean isMultipleAnswer) {
        this.isMultipleAnswer = isMultipleAnswer;
    }

    public boolean hasIsMultipleAnswer() {
        return !UtilEntity.isEmpty(this.isMultipleAnswer);
    }

    public boolean isMultipleAnswerTrue() {
        return this.hasIsMultipleAnswer() && Boolean.TRUE.equals(this.isMultipleAnswer);
    }

    /** SUCCESS RATE **/

    public Double getSuccessRate() {
        return this.successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    public boolean hasSuccessRate() {
        return !UtilEntity.isEmpty(this.successRate);
    }

    /** TIMER **/

    public Integer getTimer() {
        return this.timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public boolean hasTimert() {
        return !UtilEntity.isEmpty(this.timer);
    }

}
