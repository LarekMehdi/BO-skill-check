package fr.perso.skillcheck.question.dto;

import java.util.List;

import fr.perso.skillcheck.answer.dto.SmallAnswerDto;
import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.tag.dto.TagDto;
import fr.perso.skillcheck.test.dto.SmallTestDto;
import fr.perso.skillcheck.user.dto.SmallUserDto;
import fr.perso.skillcheck.utils.UtilEntity;

public class QuestionDetailsDto {
    private Long                        id;
    private String                      content;
    private String                      code;
    private Boolean                     isMultipleAnswer;
    private Double                      successRate;
    private Integer                     doneCount;
    private Integer                     correctCount;
    private Integer                     timeLimit;
    private Difficulty                  difficulty;
    private SmallUserDto                createdBy;
    private List<SmallAnswerDto>        answerList;
    private List<TagDto>                tagList;
    private List<SmallTestDto>          testList;

    public QuestionDetailsDto() {}

    public QuestionDetailsDto(Long id, String content, String code, Boolean isMultipleAnswer, Double successRate, Integer doneCount, Integer correctCount,
                                Integer timeLimit, Difficulty difficulty, SmallUserDto createdBy, List<SmallAnswerDto> answerList, List<TagDto> tagList, List<SmallTestDto> testList) {
        this.id = id;
        this.content = content;
        this.code = code;
        this.isMultipleAnswer = isMultipleAnswer;
        this.successRate = successRate;
        this.doneCount = doneCount;
        this.correctCount = correctCount;
        this.timeLimit = timeLimit;
        this.difficulty = difficulty;
        this.createdBy = createdBy;
        this.answerList = answerList;
        this.tagList = tagList;
        this.testList = testList;
    }

    public QuestionDetailsDto(Question question, SmallUserDto createdBy) {
        this.id = question.getId();
        this.content = question.getContent();
        this.code = question.getCode();
        this.isMultipleAnswer = question.getIsMultipleAnswer();
        this.successRate = question.getSuccessRate();
        this.doneCount = question.getDoneCount();
        this.correctCount = question.getCorrectCount();
        this.timeLimit = question.getTimeLimit();
        this.difficulty = question.getDifficulty();
        this.createdBy = createdBy;
        // this.answerList = answerList;
        // this.tagList = tagList;
        // this.testList = testList;
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

    /** DONE COUNT **/

    public Integer getDoneCount() {
        return this.doneCount;
    }

    public void setDoneCount(Integer doneCount) {
        this.doneCount = doneCount;
    }

    public boolean hasDoneCount() {
        return !UtilEntity.isEmpty(this.doneCount);
    }

    /** CORRECT COUNT **/

    public Integer getCorrectCount() {
        return this.correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    public boolean hasCorrectCount() {
        return !UtilEntity.isEmpty(this.correctCount);
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

    /** CREATED BY **/

    public SmallUserDto getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(SmallUserDto createdBy) {
        this.createdBy = createdBy;
    }

    public boolean hasCreatedBy() {
        return !UtilEntity.isEmpty(this.createdBy);
    }

    /** ANSWER LIST **/

    public List<SmallAnswerDto> getAnswerList() {
        return this.answerList;
    }

    public void setAnswerList(List<SmallAnswerDto> answerList) {
        this.answerList = answerList;
    }

    public boolean hasAnswerList() {
        return !UtilEntity.isEmpty(this.answerList);
    }

    /** TAG LIST **/

    public List<TagDto> getTagList() {
        return this.tagList;
    }

    public void setTagList(List<TagDto> tagList) {
        this.tagList = tagList;
    }

    public boolean hasTagList() {
        return !UtilEntity.isEmpty(this.tagList);
    }

    /** TEST LIST **/

    public List<SmallTestDto> getTestList() {
        return this.testList;
    }

    public void setTestList(List<SmallTestDto> testList) {
        this.testList = testList;
    }

    public boolean hasTestList() {
        return !UtilEntity.isEmpty(this.testList);
    }

}
