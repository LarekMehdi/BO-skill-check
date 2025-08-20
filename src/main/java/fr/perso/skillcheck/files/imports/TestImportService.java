package fr.perso.skillcheck.files.imports;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.queryServices.TestQueryService;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.test.dto.TestExportDto;
import fr.perso.skillcheck.utils.UtilExcel;
import fr.perso.skillcheck.utils.UtilMapper;

@Service
public class TestImportService {

    @Autowired
    private TestQueryService     testQueryService;

    public List<Test> importExcel(InputStream inputStream, UserPrincipal user) {
        List<TestExportDto> dtos = UtilExcel.importExcel(inputStream, user);

        // initialisation des listes à créer en base
        List<Test> testList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        List<Answer> answerList = new ArrayList<>();

        // regroupement des données
        Map<Long, Long> testBaseIdByImportId = new HashMap<>();
        Map<Long, Long> questionBaseIdByImportId = new HashMap<>();

        for (TestExportDto dto : dtos) {
            Test test = new Test(dto);

            testList.add(test);
            testBaseIdByImportId.put(dto.getId(), test.getId());

            List<Question> questions = UtilMapper.mapQuestionExportListToQuestions(dto.getQuestions());
            questionList.addAll(questions);

            //questionBaseIdByImportId.put(dto.getQuestions().str, null)

        }




        // save des tests
        this.testQueryService.createMany(testList);

        // save des questions

        // save des answers

        // return test
        return new ArrayList<>();
    }
    
}
