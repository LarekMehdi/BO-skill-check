package fr.perso.skillcheck.question.dto;

import java.util.List;

import fr.perso.skillcheck.answer.dto.SmallAnswerDto;
import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.utils.UtilEntity;

public class QuestionExportDto {
    private Long                    id;
    private String                  content;
    private String                  code;
    private Boolean                 isMultipleAnswer;
    private Integer                 timeLimit;
    private Difficulty              difficulty;
    private Long                    createdBy;
    private List<SmallAnswerDto>    answers;

    public QuestionExportDto() {}

    public QuestionExportDto(Long id, String content, String code, Boolean isMultipleAnswer, Integer timeLimit, Difficulty difficulty, Long createdBy, List<SmallAnswerDto> answers) {
        this.id = id;
        this.content = content;
        this.code = code;
        this.isMultipleAnswer = isMultipleAnswer;
        this.timeLimit = timeLimit;
        this.difficulty = difficulty;
        this.createdBy = createdBy;
        this.answers = answers;
    }

    public QuestionExportDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
        this.code = question.getCode();
        this.isMultipleAnswer = question.getIsMultipleAnswer();
        this.timeLimit = question.getTimeLimit();
        this.difficulty = question.getDifficulty();
        this.createdBy = question.getCreatedBy();
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
        return !UtilEntity.isEmpty(code);
    }

    /** IS MULTIPLE ANSWER **/

    public Boolean getIsMultipleAnswer() {
        return this.isMultipleAnswer;
    }

    public void setIsMultipleAnswer(Boolean isMultipleAnswer) {
        this.isMultipleAnswer = isMultipleAnswer;
    }

    public boolean isMultipleAnswerTrue() {
        return this.hasIsMultipleAnswer() && Boolean.TRUE.equals(this.isMultipleAnswer);
    }

    public boolean hasIsMultipleAnswer() {
        return !UtilEntity.isEmpty(isMultipleAnswer);
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

    /** DIFFICULTY **/

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean hasDifficulty() {
        return !UtilEntity.isEmpty(difficulty);
    }

    /** CREATED BY **/

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public boolean hasCreatedBy() {
        return !UtilEntity.isEmpty(createdBy);
    }

    /** ANSWERS **/

    public List<SmallAnswerDto> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<SmallAnswerDto> answers) {
        this.answers = answers;
    }

    public boolean hasAnswers() {
        return !UtilEntity.isEmpty(answers);
    }
}
