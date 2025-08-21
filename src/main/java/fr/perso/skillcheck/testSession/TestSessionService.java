package fr.perso.skillcheck.testSession;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.AnswerService;
import fr.perso.skillcheck.exceptions.NotFoundException;
import fr.perso.skillcheck.queryServices.TestQueryService;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.question.QuestionService;
import fr.perso.skillcheck.question.dto.ResultQuestionDto;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.testHasQuestion.TestHasQuestion;
import fr.perso.skillcheck.testHasQuestion.TestHasQuestionService;
import fr.perso.skillcheck.testSession.dto.TestSessionDetailsDto;
import fr.perso.skillcheck.userHasAnswer.UserHasAnswer;
import fr.perso.skillcheck.userHasAnswer.UserHasAnswerService;
import fr.perso.skillcheck.utils.UtilMapper;

@Service
public class TestSessionService {

    @Autowired
    private TestSessionRepository       tsRepository;

    @Autowired
    private TestQueryService            testQueryService;

    @Autowired
    private TestHasQuestionService      thqService;

    @Autowired
    private QuestionService             questionService;

    @Autowired
    private AnswerService               answerService;

    @Autowired
    private UserHasAnswerService        uhaService;

    /** FIND **/

    public TestSession findById(Long id) {
        return this.tsRepository.findById(id).orElseThrow(() -> new NotFoundException("No test session found with id " + id));
    }

    public TestSessionDetailsDto findTestSessionDetails(Long id, UserPrincipal user) {
        // récup du test
        TestSession session = this.findById(id);
        Test test = this.testQueryService.findById(session.getTest().getId());
        
        // récup des questions/réponses
        List<TestHasQuestion> thqList = this.thqService.findAllByTestId(test.getId());
        List<Long> questionIds = thqList.stream().map(thq -> thq.getQuestion().getId()).collect(Collectors.toList());
        List<Question> questionList = this.questionService.findAllByIds(questionIds);
        List<Answer> answerList = this.answerService.findAllByQuestionIds(questionIds);

        List<UserHasAnswer> uhaList = this.uhaService.findAllBySessionId(id);
        List<Long> userAnswerIds = uhaList.stream().map(uha -> uha.getAnswer().getId()).collect(Collectors.toList());

        // regroupement des données
        Map<Long, List<Answer>> answersByQuestionId = answerList.stream().collect(Collectors.groupingBy(a -> a.getQuestion().getId(), Collectors.toList()));
        List<ResultQuestionDto> questionDtos = UtilMapper.mapQuestionListToResultQuestionDtos(questionList, answersByQuestionId, userAnswerIds);
        TestSessionDetailsDto dto = new TestSessionDetailsDto(session, test, questionDtos);
        
        dto.setUserId(user.getId());

        return dto;
    }

    /** FIND ALL **/

    public List<TestSession> findAllByUserId(Long userId) {
        return this.tsRepository.findAllByUserId(userId);
    }

    /** CREATE **/

    public TestSession create(TestSession ts) {
        return this.tsRepository.save(ts);
    }

    /** UPDATE **/

    public TestSession update(TestSession ts) {
        return this.tsRepository.save(ts);
    }


    
}
