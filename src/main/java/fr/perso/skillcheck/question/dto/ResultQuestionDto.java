package fr.perso.skillcheck.question.dto;

import java.util.List;

import fr.perso.skillcheck.answer.dto.ResultAnswerDto;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.utils.UtilEntity;

public class ResultQuestionDto {
    private Long                        id;
    private String                      content;
    private String                      code;
    private List<ResultAnswerDto>       choices;
    private Boolean                     isCorrect;
    private Boolean                     isMultipleAnswer;

    public ResultQuestionDto() {}

    public ResultQuestionDto(Long id, String content, String code, List<ResultAnswerDto> choices, Boolean isMultipleAnswer) {
        this.id = id;
        this.content = content;
        this.code = code;
        this.choices = choices;
        this.isMultipleAnswer = isMultipleAnswer;
    }

    public ResultQuestionDto(Long id, String content, String code, List<ResultAnswerDto> choices) {
        this.id = id;
        this.content = content;
        this.code = code;
        this.choices = choices;
    }

    public ResultQuestionDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
        this.code = question.getCode();
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

    /** CHOICES **/

    public List<ResultAnswerDto> getChoices() {
        return this.choices;
    }

    public void setChoices(List<ResultAnswerDto> choices) {
        this.choices = choices;
    }

    public boolean hasChoices() {
        return !UtilEntity.isEmpty(this.choices);
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

}
