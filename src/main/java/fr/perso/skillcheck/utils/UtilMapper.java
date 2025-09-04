package fr.perso.skillcheck.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.dto.AnswerDto;
import fr.perso.skillcheck.answer.dto.ResultAnswerDto;
import fr.perso.skillcheck.answer.dto.SmallAnswerDto;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.question.dto.QuestionExportDto;
import fr.perso.skillcheck.question.dto.QuestionSmallDto;
import fr.perso.skillcheck.question.dto.ResultQuestionDto;
import fr.perso.skillcheck.tag.Tag;
import fr.perso.skillcheck.tag.dto.TagDto;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.test.dto.SmallTestDto;
import fr.perso.skillcheck.test.dto.TestExportDto;
import fr.perso.skillcheck.testSession.TestSession;
import fr.perso.skillcheck.testSession.dto.UserTestSessionDto;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.user.dto.SmallUserDto;

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

                List<Answer> correctAnswers = answerList.stream().filter(Answer::isCorrectTrue).collect(Collectors.toList());
                List<Answer> userAnswers = answerList.stream().filter(a ->userAnswerIds.contains(a.getId())).collect(Collectors.toList());
                boolean isQuestionCorrect = q.areAnswersCorrect(userAnswers, correctAnswers);
                dto.setIsCorrect(isQuestionCorrect);
            }

            dtos.add(dto);
        }
        return dtos;
    }

    public static List<Question> mapQuestionExportListToQuestions(List<QuestionExportDto> dtos) {
        List<Question> questions = new ArrayList<>();
        for (QuestionExportDto dto : dtos) {
            Question question = new Question(dto);

            questions.add(question);
        }
        return questions;
    }

    public static List<QuestionExportDto> mapQuestionListToQuestionExportDtos(List<Question> questionList, Map<Long, List<Answer>> answersByQuestionId, Map<Long, List<Tag>> tagsByQuestionId) {
        List<QuestionExportDto> dtos = new ArrayList<>();
        for (Question question : questionList) {
            Long questionId = question.getId();
            if (answersByQuestionId.containsKey(questionId)) {
                List<Answer> answerList = answersByQuestionId.get(questionId);
                QuestionExportDto dto = new QuestionExportDto(question);
                List<SmallAnswerDto> aList = mapAnswerListToSmallAnswerDtos(answerList);
                dto.setAnswers(aList);

                if (tagsByQuestionId.containsKey(questionId)) {
                    List<Tag> tagList = tagsByQuestionId.get(questionId);
                    List<TagDto> tagDtos = mapTagListToTagDtos(tagList);
                    dto.setTags(tagDtos);
                }

                dtos.add(dto);
            }
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

    public static List<SmallAnswerDto> mapAnswerListToSmallAnswerDtos(List<Answer> answerList) {
        List<SmallAnswerDto> dtos = new ArrayList<>();
        for (Answer answer : answerList) {
            SmallAnswerDto dto = new SmallAnswerDto(answer);

            dtos.add(dto);
        }
        return dtos;
    }

    public static List<Answer> mapSmallAnswerListToAnswers(List<SmallAnswerDto> dtos) {
        List<Answer> answers = new ArrayList<>();
        for (SmallAnswerDto dto : dtos) {
            Answer answer = new Answer(dto);

            answers.add(answer);
        }
        return answers;
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

    /** TEST **/

    public static List<TestExportDto> mapTestListToTestExportDtos(List<Test> testList, Map<Long, List<Question>> questionsByTestId, Map<Long, List<Answer>> answersByQuestionId, Map<Long, List<Tag>> tagsByQuestionId) {
        List<TestExportDto> dtos = new ArrayList<>();
        for (Test test : testList) {
            TestExportDto dto = new TestExportDto(test);
            if (questionsByTestId.containsKey(test.getId())) {
                List<Question> questionList = questionsByTestId.get(test.getId());
                List<QuestionExportDto> questions = mapQuestionListToQuestionExportDtos(questionList, answersByQuestionId, tagsByQuestionId);
                dto.setQuestions(questions);

                dtos.add(dto);
            }
        }
        return dtos;
    }

    public static List<Test> mapTestExportListToTests(List<TestExportDto> dtos) {
        List<Test> tests = new ArrayList<>();
        for (TestExportDto dto : dtos) {
            Test test = new Test(dto);

            tests.add(test);
        }
        return tests;
    }

    public static List<SmallTestDto> mapTestListToSmallTestDtos(List<Test> tests) {
        List<SmallTestDto> dtos = new ArrayList<>();
        for (Test test : tests) {
            SmallTestDto dto = new SmallTestDto(test);

            dtos.add(dto);
        }
        return dtos;
    }

    /** USER **/

    public static List<SmallUserDto> mapUserListToSmallUserDtos(List<User> users) {
        List<SmallUserDto> dtos = new ArrayList<>();
        for (User user : users) {
            SmallUserDto dto = new SmallUserDto(user);

            dtos.add(dto);
        }
        return dtos;
    }

    /** TAG **/

    public static List<TagDto> mapTagListToTagDtos(List<Tag> tags) {
        List<TagDto> dtos = new ArrayList<>();
        for (Tag tag : tags) {
            TagDto dto = new TagDto(tag);

            dtos.add(dto);
        }
        return dtos;
    }

}
