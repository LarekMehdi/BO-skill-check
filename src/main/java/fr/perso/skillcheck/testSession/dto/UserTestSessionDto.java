package fr.perso.skillcheck.testSession.dto;

import java.time.LocalDateTime;

import fr.perso.skillcheck.utils.UtilEntity;

public class UserTestSessionDto {
    private Long            sessionId;
    private Long            testId;
    private String          testTitle;
    private LocalDateTime   createdAt;
    private Double          successRate;

    public UserTestSessionDto() {}

    public UserTestSessionDto(Long sessionId, Long testId, String testTitle, LocalDateTime createdAt, Double successRate) {
        this.sessionId = sessionId;
        this.testId = testId;
        this.testTitle = testTitle;
        this.createdAt = createdAt;
        this.successRate = successRate;
    }

    /** SESSION ID **/

    public Long getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public boolean hasSessionId() {
        return !UtilEntity.isEmpty(this.sessionId);
    }

    /** TEST ID **/

    public Long getTestId() {
        return this.testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public boolean hasTestId() {
        return !UtilEntity.isEmpty(this.testId);
    }

    /** TEST TITLE **/

    public String getTestTitle() {
        return this.testTitle;
    }

    public void setTestId(String testTitle) {
        this.testTitle = testTitle;
    }

    public boolean hasTestTitle() {
        return !UtilEntity.isEmpty(this.testTitle);
    }

    /** CREATED AT **/

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean hasCreatedAt() {
        return !UtilEntity.isEmpty(this.createdAt);
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
}
