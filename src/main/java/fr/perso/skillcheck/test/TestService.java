package fr.perso.skillcheck.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.exceptions.NotFoundException;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.question.QuestionService;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.test.dto.TestDetailsDto;
import fr.perso.skillcheck.test.dto.TestDto;
import fr.perso.skillcheck.test.dto.UpdateTestQuestionDto;
import fr.perso.skillcheck.testHasQuestion.TestHasQuestion;
import fr.perso.skillcheck.testHasQuestion.TestHasQuestionService;
import fr.perso.skillcheck.testHasQuestion.dto.UpdateTestQuestionsResultDto;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.UtilEntity;

@Service
public class TestService {

    @Autowired
    private TestRepository          testRepository;

    @Autowired
    private TestHasQuestionService  thqService;

    @Autowired
    private QuestionService         questionService;

    /** FIND ALL **/

    // TODO: recuperer les tags en meme temps
    public Page<Test> findAllWithPagination(GenericFilter filter) {
        filter.initGenericFilterIfNeeded();
        Pageable pageable = filter.toPageable();
        return this.testRepository.findAllWithPagination(pageable);
    }

    /** FIND **/

    public Test findById(Long id) {
        Test test = this.testRepository.findById(id).orElseThrow(() -> new NotFoundException("No test found with id " + id));
        return test;
    }

    public TestDetailsDto findDtoById(Long id) {
        Test test = this.testRepository.findById(id).orElseThrow(() -> new NotFoundException("No test found with id " + id));
        TestDetailsDto dto = new TestDetailsDto(test);

        List<TestHasQuestion> thqList = this.thqService.findAllByTestId(id);
        List<Long> questionIds = thqList.stream().map((thq) -> thq.getQuestion().getId()).collect(Collectors.toList());
        dto.setQuestionIds(questionIds);

        List<Question> questionList = this.questionService.findAllByIds(questionIds);
        dto.setSuccessRate(UtilEntity.computeSuccessRate(questionList));
        dto.setTimeLimit(UtilEntity.computeTimeLimit(questionList));

        return dto;
    }

    /** UPDATE **/

    public UpdateTestQuestionsResultDto updateQuestions(UpdateTestQuestionDto dto) {
   
        List<TestHasQuestion> currentThqList = this.thqService.findAllByTestId(dto.getTestId());

        Set<Long> currentQuestionIds = currentThqList.stream()
            .map(thq -> thq.getQuestion().getId())
            .collect(Collectors.toSet());

        Set<Long> newQuestionIds = new HashSet<>(dto.getQuestionIds());

        List<Long> questionIdsToRemove = currentQuestionIds.stream()
            .filter(id -> !newQuestionIds.contains(id))
            .collect(Collectors.toList());

        List<Long> questionIdsToAdd = newQuestionIds.stream()
            .filter(id -> !currentQuestionIds.contains(id))
            .collect(Collectors.toList());

        // Ajout des nouvelles questions
        List<TestHasQuestion> newThqList = new ArrayList<>();
        for (Long id : questionIdsToAdd) {
            TestHasQuestion thq = new TestHasQuestion();
            thq.setTest(new Test(dto.getTestId()));
            thq.setQuestion(new Question(id));
            newThqList.add(thq);
        }
        this.thqService.createMany(newThqList);

        // Suppression des questions retirées
        this.thqService.deleteAllByTestIdAndQuestionIds(dto.getTestId(), questionIdsToRemove);

        UpdateTestQuestionsResultDto result = new UpdateTestQuestionsResultDto(newThqList.size(), questionIdsToRemove.size());
        return result;
    }

    /** CREATE **/

    public Test create(TestDto dto, UserPrincipal user) {
        Test test = new Test(dto);
        test.setCreatedBy(user.getId());
        return this.testRepository.save(test);
    }
    
}
