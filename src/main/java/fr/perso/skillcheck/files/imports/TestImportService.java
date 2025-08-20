package fr.perso.skillcheck.files.imports;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.AnswerService;
import fr.perso.skillcheck.answer.dto.SmallAnswerDto;
import fr.perso.skillcheck.queryServices.TestQueryService;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.question.QuestionService;
import fr.perso.skillcheck.question.dto.QuestionExportDto;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.test.dto.TestExportDto;
import fr.perso.skillcheck.utils.UtilExcel;
import fr.perso.skillcheck.utils.UtilMapper;

@Service
public class TestImportService {

    @Autowired
    private TestQueryService    testQueryService;

    @Autowired
    private QuestionService     questionService;

    @Autowired
    private AnswerService       answerService;

    public List<Test> importExcel(InputStream inputStream, UserPrincipal user) {
        List<TestExportDto> dtos = UtilExcel.importExcel(inputStream, user);

        // initialisation des listes à créer en base
        List<Test> testList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        List<Answer> answerList = new ArrayList<>();

        // regroupement des données
        Map<Long, Long> testBaseIdByImportId = new HashMap<>();
        Map<Long, Long> questionBaseIdByImportId = new HashMap<>();
        Map<Long, List<Answer>> answersByQuestionId = new HashMap<>();

        // sauvegarde des Tests en base
        testList = dtos.stream().map(d -> new Test(d)).collect(Collectors.toList());
        testList = this.testQueryService.createMany(testList);

        // suivi des ids des Tests
        for (int i=0; i<dtos.size(); i++) {
            testBaseIdByImportId.put(dtos.get(i).getId(), testList.get(i).getId());
        }

        // création des questions
        for (TestExportDto dto : dtos) {
            Long testBaseId = testBaseIdByImportId.get(dto.getId());

            for (QuestionExportDto qDto : dto.getQuestions()) {
                Question q = new Question(qDto);
                questionList.add(q);

                // TestHasQuestion


            }
        }

        // sauvegarde des questions en base
        questionList = this.questionService.createMany(questionList);

        // suivi des ids des Questions
        int idx = 0;
        for (TestExportDto dto : dtos) {
            for (QuestionExportDto qDto : dto.getQuestions()) {
                questionBaseIdByImportId.put(qDto.getId(), questionList.get(idx).getId());

                // création des answers
                List<SmallAnswerDto> aDtos = qDto.getAnswers();
                List<Answer> answers = UtilMapper.mapSmallAnswerListToAnswers(aDtos);
                for (Answer a : answers) {
                    a.setQuestion(new Question(questionList.get(idx).getId()));
                }
                answerList.addAll(answers);
                idx++;
            }
        }

        // sauvegarde des answers en base
        answerList = this.answerService.createMany(answerList);

        return testList;
    }
    
}
