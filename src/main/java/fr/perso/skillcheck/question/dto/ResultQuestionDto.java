package fr.perso.skillcheck.question.dto;

import java.util.List;

import fr.perso.skillcheck.answer.dto.ResultAnswerDto;
import fr.perso.skillcheck.question.Question;

public class ResultQuestionDto {
    private Long                        id;
    private String                      content;
    private List<ResultAnswerDto>       choices;

    public ResultQuestionDto() {}

    public ResultQuestionDto(Long id, String content, List<ResultAnswerDto> choices) {
        this.id = id;
        this.content = content;
        this.choices = choices;
    }

    public ResultQuestionDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
    }

}
