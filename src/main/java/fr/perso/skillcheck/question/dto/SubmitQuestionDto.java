package fr.perso.skillcheck.question.dto;

import java.util.List;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotNull;

public class SubmitQuestionDto {

    @NotNull(message = "L'id de la question est requis")
    private Long        questionId;

    private List<Long>  selectedAnswerIds;

    public SubmitQuestionDto() {}

    public SubmitQuestionDto(Long questionId, List<Long> selectedAnswerIds) {
        this.questionId = questionId;
        this.selectedAnswerIds = selectedAnswerIds;
    }

    /** ID **/

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public boolean hasQuestionId() {
        return !UtilEntity.isEmpty(questionId);
    }

    /** SELECTED ANSWER IDS **/

    public List<Long> getSelectedAnswerIds() {
        return this.selectedAnswerIds;
    }

    public void setSelectedAnswerIds(List<Long> selectedAnswerIds) {
        this.selectedAnswerIds = selectedAnswerIds;
    }

    public boolean hasSelectedAnswerIds() {
        return !UtilEntity.isEmpty(selectedAnswerIds);
    }
}
