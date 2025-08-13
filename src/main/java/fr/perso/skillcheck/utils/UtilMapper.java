package fr.perso.skillcheck.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.dto.AnswerDto;
import fr.perso.skillcheck.answer.dto.ResultAnswerDto;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.question.dto.QuestionSmallDto;
import fr.perso.skillcheck.question.dto.ResultQuestionDto;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.testSession.TestSession;
import fr.perso.skillcheck.testSession.dto.UserTestSessionDto;

public abstract class UtilMapper {

    /** QUESTION **/
    
    public static List<QuestionSmallDto> mapQuestionListToQuestionSmallDtos(List<Question> questions) {
        List<QuestionSmallDto> dtos = new ArrayList<>();
        for (Question q : questions) {
            dtos.add(new QuestionSmallDto(q));
        }
        return dtos;
    }

    public static List<ResultQuestionDto> mapQuestionListToResultQuestionDtos(List<Question> questions, Map<Long, List<Answer>> answersByQuestionId, List<Long> userAnswerIds) {
        List<ResultQuestionDto> dtos = new ArrayList<>();
        for (Question q : questions) {
            ResultQuestionDto dto = new ResultQuestionDto(q);

            if (answersByQuestionId.containsKey(q.getId())) {
                List<Answer> answerList = answersByQuestionId.get(q.getId());
                List<ResultAnswerDto> answerDtos = mapAnswerListToResultAnswerDtos(answerList, userAnswerIds);
                dto.setChoices(answerDtos);
            }

            dtos.add(dto);
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

    public static List<ResultAnswerDto> mapAnswerListToResultAnswerDtos(List<Answer> answers, List<Long> userAnswerIds) {
        List<ResultAnswerDto> dtos = new ArrayList<>();
        for (Answer a : answers) {
            ResultAnswerDto dto = new ResultAnswerDto(a);
            if (userAnswerIds.contains(a.getId())) dto.setIsSelectedByUser(true);

            dtos.add(dto);
        }
        return dtos;
    }

    /** SESSIONS **/

    public static List<UserTestSessionDto> mapTestSessionListToUserTestSessionDtos(List<TestSession> sessions, Map<Long, Test> testById) {
        List<UserTestSessionDto> dtos = new ArrayList<>();
        for (TestSession session : sessions) {
            UserTestSessionDto dto = new UserTestSessionDto(session);
            Test test = testById.get(session.getTest().getId());
            dto.setTestTitle(test.getTitle());

            dtos.add(dto);
        }
        return dtos;
    }

}
