package fr.perso.skillcheck.question.dto;

import java.util.List;

import fr.perso.skillcheck.answer.dto.SmallAnswerDto;
import fr.perso.skillcheck.constants.Difficulty;

public class QuestionExportDto {
    private Long                    id;
    private String                  content;
    private String                  code;
    private Boolean                 isMultipleAnswer;
    private Integer                 timeLimite;
    private Difficulty              difficulty;
    private Long                    createdBy;
    private List<SmallAnswerDto>    answers;
}
