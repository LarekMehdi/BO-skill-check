package fr.perso.skillcheck.test.dto;

import java.util.List;

import fr.perso.skillcheck.question.dto.TakeQuestionDto;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.utils.UtilEntity;

public class TakeTestDto {
    private Long                    id;
    private String                  title;
    private List<TakeQuestionDto>   questionList;

    public TakeTestDto() {}

    public TakeTestDto(Long id, String title, List<TakeQuestionDto> questionList) {
        this.id = id;
        this.title = title;
        this.questionList = questionList;
    }

    public TakeTestDto(Test test) {
        this.id = test.getId();
        this.title = test.getTitle();
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

    /** TITLE **/

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasTitle() {
        return !UtilEntity.isEmpty(title);
    }

    /** TITLE **/

    public List<TakeQuestionDto> getQuestionList() {
        return this.questionList;
    }

    public void setQuestionList(List<TakeQuestionDto> questionList) {
        this.questionList = questionList;
    }

    public boolean hasQuestionList() {
        return !UtilEntity.isEmpty(questionList);
    }
}
