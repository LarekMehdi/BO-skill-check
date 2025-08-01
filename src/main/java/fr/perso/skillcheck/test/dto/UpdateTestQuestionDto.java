package fr.perso.skillcheck.test.dto;

import java.util.List;

import fr.perso.skillcheck.utils.UtilEntity;

public class UpdateTestQuestionDto {
    private Long        testId;
    private List<Long>  questionIds;

    public UpdateTestQuestionDto() {}

    public UpdateTestQuestionDto(Long testId, List<Long> questionIds) {
        this.testId = testId;
        this.questionIds = questionIds;
    }

    /** TEST ID **/

    public Long getTestId() {
        return this.testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public boolean hasTestId() {
        return !UtilEntity.isEmpty(testId);
    }

    /** QUESTION IDS **/

    public List<Long> getQuestionIds() {
        return this.questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public boolean hasQuestionIds() {
        return !UtilEntity.isEmpty(questionIds);
    }
}
