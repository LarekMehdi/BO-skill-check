package fr.perso.skillcheck.utils;

import java.util.ArrayList;
import java.util.List;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.dto.AnswerDto;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.question.dto.QuestionSmallDto;

public abstract class UtilMapper {

    /** QUESTION **/
    
    public static List<QuestionSmallDto> mapQuestionListToQuestionSmallDtos(List<Question> questions) {
        List<QuestionSmallDto> dtos = new ArrayList<>();
        for (Question q : questions) {
            dtos.add(new QuestionSmallDto(q));
        }
        return dtos;
    }

    /** ANSWER **/

    public static List<AnswerDto> mapAnswerListToAnswerDtos(List<Answer> answers) {
        List<AnswerDto> dtos = new ArrayList<>();
        for (Answer a : answers) {
            dtos.add(new AnswerDto(a));
        }
        return dtos;
    }

}
