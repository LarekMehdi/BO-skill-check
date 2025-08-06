package fr.perso.skillcheck.testSession.dto;

import java.time.LocalDateTime;

import fr.perso.skillcheck.testSession.TestSession;
import fr.perso.skillcheck.utils.UtilEntity;

public class TestSessionDto {
    
    private Long            id;
    private Long            testId;
    private Long            userId;
    private LocalDateTime   createdAt;

    public TestSessionDto() {}

    public TestSessionDto(Long id, Long testId, Long userId, LocalDateTime createdAt) {
        this.id = id;
        this.testId = testId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public TestSessionDto(Long testId, Long userId, LocalDateTime createdAt) {
        this.testId = testId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public TestSessionDto(TestSession session) {
        this.id = session.getId();
        this.testId = session.getTest().getId();
        this.userId = session.getUser().getId();
        this.createdAt = session.getCreatedAt();
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

    /** USER ID **/

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean hasUserId() {
        return !UtilEntity.isEmpty(this.userId);
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
