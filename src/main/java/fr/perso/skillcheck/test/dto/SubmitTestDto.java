package fr.perso.skillcheck.test.dto;

import java.util.List;

import fr.perso.skillcheck.question.dto.SubmitQuestionDto;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotNull;

public class SubmitTestDto {

    @NotNull(message = "L'id du test est requis")
    private Long                        id;

    private List<SubmitQuestionDto>     answers;

    public SubmitTestDto() {}

    public SubmitTestDto(Long id, List<SubmitQuestionDto> answers) {
        this.id = id;
        this.answers = answers;
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

    /** ANSWERS **/

    public List<SubmitQuestionDto> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<SubmitQuestionDto> answers) {
        this.answers = answers;
    }

    public boolean hasAnswers() {
        return !UtilEntity.isEmpty(answers);
    }
}
