package fr.perso.skillcheck.testSession.dto;

import java.time.LocalDateTime;
import java.util.List;

import fr.perso.skillcheck.question.dto.ResultQuestionDto;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.testSession.TestSession;
import fr.perso.skillcheck.utils.UtilEntity;

public class TestSessionDetailsDto {
    private Long                        sessionId;
    private Long                        testId;
    private String                      testTitle;
    private List<ResultQuestionDto>     questionList;
    private LocalDateTime               createdAt;

    public TestSessionDetailsDto() {}

    public TestSessionDetailsDto(Long sessionId, Long testId, String testTitle, List<ResultQuestionDto> questionList, LocalDateTime createdAt) {
        this.sessionId = sessionId;
        this.testId = testId;
        this.testTitle = testTitle;
        this.questionList = questionList;
        this.createdAt = createdAt;
    }

    public TestSessionDetailsDto(TestSession session) {
        this.sessionId = session.getId();
        this.createdAt = session.getCreatedAt();
    }

    public TestSessionDetailsDto(TestSession session, Test test) {
        this.sessionId = session.getId();
        this.createdAt = session.getCreatedAt();
        this.testId = test.getId();
        this.testTitle = test.getTitle();
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

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public boolean hasTestTitle() {
        return !UtilEntity.isEmpty(this.testTitle);
    }

    /** QUESTION LIST **/

    public List<ResultQuestionDto> getQuestionList() {
        return this.questionList;
    }

    public void setTestTitle(List<ResultQuestionDto> questionList) {
        this.questionList = questionList;
    }

    public boolean hasQuestionList() {
        return !UtilEntity.isEmpty(this.questionList);
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
}
