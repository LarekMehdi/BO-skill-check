package fr.perso.skillcheck.question.dto;

import java.util.List;

import fr.perso.skillcheck.answer.dto.AnswerDto;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.utils.UtilEntity;

public class TakeQuestionDto {
    
    private Long                id;
    private String              content;
    private String              code;
    private Integer             timeLimit;
    private Boolean             isMultipleAnswer;
    private List<AnswerDto>     choices;

    public TakeQuestionDto() {}

    public TakeQuestionDto(Long id, String content, String code, Integer timeLimit, Boolean isMultipleAnswer, List<AnswerDto> choices) {
        this.id = id;
        this.content = content;
        this.timeLimit = timeLimit;
        this.isMultipleAnswer = isMultipleAnswer;
        this.choices = choices;
    }

    public TakeQuestionDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
        this.code = question.getCode();
        this.timeLimit = question.getTimeLimit();
        this.isMultipleAnswer = question.getIsMultipleAnswer();
    }

    /** ID **/

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasId() {
        return !UtilEntity.isEmpty(id);
    }

    /** CONTENT **/

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean hasContent() {
        return !UtilEntity.isEmpty(content);
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

    /** TIME LIMIT **/

    public Integer getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean hasTimeLimit() {
        return !UtilEntity.isEmpty(timeLimit);
    }

    /** IS MULTIPLE ANSWER **/

    public Boolean getIsMultipleAnswer() {
        return this.isMultipleAnswer;
    }

    public void setIsMultipleAnswer(Boolean isMultipleAnswer) {
        this.isMultipleAnswer = isMultipleAnswer;
    }

    public boolean hasIsMultipleAnswer() {
        return !UtilEntity.isEmpty(isMultipleAnswer);
    }

    public boolean isMultipleAnswerTrue() {
        return this.hasIsMultipleAnswer() && Boolean.TRUE.equals(this.isMultipleAnswer);
    }

    /** TIME LIMIT **/

    public List<AnswerDto> getChoices() {
        return this.choices;
    }

    public void setChoices(List<AnswerDto> choices) {
        this.choices = choices;
    }

    public boolean hasChoices() {
        return !UtilEntity.isEmpty(choices);
    }
}
