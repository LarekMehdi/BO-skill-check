package fr.perso.skillcheck.question.dto;

import java.util.List;

import fr.perso.skillcheck.answer.dto.ResultAnswerDto;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.utils.UtilEntity;

public class ResultQuestionDto {
    private Long                        id;
    private String                      content;
    private String                      code;
    private List<ResultAnswerDto>       choices;

    public ResultQuestionDto() {}

    public ResultQuestionDto(Long id, String content, String code, List<ResultAnswerDto> choices) {
        this.id = id;
        this.content = content;
        this.code = code;
        this.choices = choices;
    }

    public ResultQuestionDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
        this.code = question.getCode();
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

    /** CHOICES **/

    public List<ResultAnswerDto> getChoices() {
        return this.choices;
    }

    public void setChoices(List<ResultAnswerDto> choices) {
        this.choices = choices;
    }

    public boolean hasChoices() {
        return !UtilEntity.isEmpty(this.choices);
    }

}
