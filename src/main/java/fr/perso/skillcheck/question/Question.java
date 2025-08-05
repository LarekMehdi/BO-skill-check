package fr.perso.skillcheck.question;

import fr.perso.skillcheck.constants.Difficulty;
import fr.perso.skillcheck.question.dto.QuestionDto;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String  content;

    @Column(nullable = false)
    private Boolean isMultipleAnswer;

    @Column(nullable = false)
    private Double  successRate;

    @Column(nullable = false)
    private Integer doneCount;

    @Column(nullable = false)
    private Integer correctCount;

    @Column(nullable = false)
    private Integer timeLimit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Column(nullable = false)
    private Long    createdBy;

    public Question() {}

    public Question(Long id) {
        this.id = id;
    }

    public Question(Long id, String content, Boolean isMultipleAnswer, Double successRate, Integer timeLimit) {
        this.id = id;
        this.content = content;
        this.isMultipleAnswer = isMultipleAnswer;
        this.successRate = successRate;
        this.timeLimit = timeLimit;
    }

    public Question(String content, Boolean isMultipleAnswer, Double successRate, Integer timeLimit) {
        this.content = content;
        this.isMultipleAnswer = isMultipleAnswer;
        this.successRate = successRate;
        this.timeLimit = timeLimit;
    }

    public Question(QuestionDto dto) {
        this.content = dto.getContent();
        this.isMultipleAnswer = dto.getIsMultipleAnswer();
        this.successRate = dto.getSuccessRate();
        this.timeLimit = dto.getTimeLimit();
        this.difficulty = dto.getDifficulty();
        this.__initCountsIfNeeded();
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

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public boolean hasCreatedBy() {
        return !UtilEntity.isEmpty(this.createdBy);
    }

    /** METHODS **/

    private void __initCountsIfNeeded(){
        if (!this.hasDoneCount()) this.doneCount = 0;
        if (!this.hasCorrectCount()) this.correctCount = 0;
        if (!this.hasSuccessRate()) this.successRate = 0.0;
    }

}




