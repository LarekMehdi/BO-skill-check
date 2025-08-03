package fr.perso.skillcheck.utils;

import java.util.ArrayList;
import java.util.List;

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

}
