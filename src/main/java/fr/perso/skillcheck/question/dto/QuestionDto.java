package fr.perso.skillcheck.question.dto;

import java.util.ArrayList;
import java.util.List;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.dto.AnswerDto;
import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class QuestionDto {

    private Long id;

    @NotBlank(message = "Le content ne doit pas être vide")
    private String  content;

    @NotNull(message = "Le champ isMultipleAnswer est requis")
    private Boolean isMultipleAnswer;
    
    private Double successRate;

    @NotNull(message = "Le champ timeLimit est requis")
    @Positive(message = "Le champ timeLimit doit être supérieur à 0")
    private Integer timeLimit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @NotEmpty(message = "La liste des réponses ne peut pas être vide")
    private List<@Valid AnswerDto> answers;

    public QuestionDto() {}

    public QuestionDto(Long id,String content, Boolean isMultipleAnswer, Integer timeLimit) {
        this.id = id;
        this.content = content;
        this.isMultipleAnswer = isMultipleAnswer;
        this.timeLimit = timeLimit;
    }

    public QuestionDto(String content, Boolean isMultipleAnswer, Integer timeLimit) {
        this.content = content;
        this.isMultipleAnswer = isMultipleAnswer;
        this.timeLimit = timeLimit;
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

    /** TIME LIMIT **/

    public Integer getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean hasTimeLimit() {
        return !UtilEntity.isEmpty(this.timeLimit);
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

    /** ANSWERS **/

    public List<AnswerDto> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public boolean hasAnswers() {
        return !UtilEntity.isEmpty(this.answers);
    }

    /** METHODS **/

    public List<Answer> getAnswersEntities() {
        List<Answer> answers = new ArrayList<>();
        for (AnswerDto a : this.answers) {
            Answer answer = new Answer(a);
            answers.add(answer);
        }
        return answers;
    }

    public List<Answer> getAnswersEntitiesWithQuestionId(Long questionId) {
        List<Answer> answers = new ArrayList<>();
        for (AnswerDto a : this.answers) {
            Answer answer = new Answer(a);
            answer.setQuestion(new Question(questionId));
            answers.add(answer);
        }
        return answers;
    }

}
