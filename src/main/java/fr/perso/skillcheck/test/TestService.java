package fr.perso.skillcheck.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.AnswerService;
import fr.perso.skillcheck.answer.dto.AnswerDto;
import fr.perso.skillcheck.exceptions.NotFoundException;
import fr.perso.skillcheck.files.imports.TestImportService;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.question.QuestionService;
import fr.perso.skillcheck.question.dto.SubmitQuestionDto;
import fr.perso.skillcheck.question.dto.TakeQuestionDto;
import fr.perso.skillcheck.questionHasTag.QuestionHasTag;
import fr.perso.skillcheck.questionHasTag.QuestionHasTagService;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.tag.Tag;
import fr.perso.skillcheck.tag.TagService;
import fr.perso.skillcheck.test.dto.SubmitTestDto;
import fr.perso.skillcheck.test.dto.TakeTestDto;
import fr.perso.skillcheck.test.dto.TestDetailsDto;
import fr.perso.skillcheck.test.dto.TestDto;
import fr.perso.skillcheck.test.dto.TestExportDto;
import fr.perso.skillcheck.test.dto.UpdateTestQuestionDto;
import fr.perso.skillcheck.test.filter.TestFilter;
import fr.perso.skillcheck.testHasQuestion.TestHasQuestion;
import fr.perso.skillcheck.testHasQuestion.TestHasQuestionService;
import fr.perso.skillcheck.testHasQuestion.dto.UpdateTestQuestionsResultDto;
import fr.perso.skillcheck.testSession.TestSession;
import fr.perso.skillcheck.testSession.TestSessionService;
import fr.perso.skillcheck.testSession.dto.TestSessionDto;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.userHasAnswer.UserHasAnswer;
import fr.perso.skillcheck.userHasAnswer.UserHasAnswerService;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.UtilAuth;
import fr.perso.skillcheck.utils.UtilEntity;
import fr.perso.skillcheck.utils.UtilExcel;
import fr.perso.skillcheck.utils.UtilMapper;


@Service
public class TestService {

    @Autowired
    private TestRepository          testRepository;

    @Autowired
    private TestHasQuestionService  thqService;

    @Autowired
    private QuestionService         questionService;

    @Autowired
    private AnswerService           answerService;

    @Autowired
    private UserHasAnswerService    uhaService;

    @Autowired
    private TestSessionService      tsService;

    @Autowired
    private TestImportService       testImportService;

    @Autowired
    private QuestionHasTagService   qhtService;

    @Autowired
    private TagService              tagService;

    /** FIND ALL **/

    // TODO: recuperer les tags en meme temps
    public Page<Test> findAllWithPagination(GenericFilter filter) {
        filter.initGenericFilterIfNeeded();
        Pageable pageable = filter.toPageable();
        return this.testRepository.findAllWithPagination(pageable);
    }

    public List<Test> findAllByIds(List<Long> ids) {
        return this.testRepository.findAllByIds(ids);
    }

    public byte[] exportTestList(TestFilter filter, UserPrincipal user) {
        if (!UtilAuth.isAdmin(user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot export datas");

        //TODO: utiliser le filter
        filter.initForExportIfNeeded();

        // récupération des données
        List<Test> testList = this.testRepository.findAllWithFilter();
        List<Long> testIds = testList.stream().map(Test::getId).collect(Collectors.toList());

        List<TestHasQuestion> thqList = this.thqService.findAllByTestIds(testIds);
        List<Long> questionIds = thqList.stream().map(thq -> thq.getQuestion().getId()).collect(Collectors.toList());

        List<Question> questionList = this.questionService.findAllByIds(questionIds);
        List<Answer> answerList = this.answerService.findAllByQuestionIds(questionIds);

        //TODO: questionHasTag
        List<QuestionHasTag> qhtList = this.qhtService.findAllByQuestionIds(questionIds);
        List<Long> tagIds = qhtList.stream().map(qht -> qht.getTag().getId()).collect(Collectors.toList());
        List<Tag> tagList = this.tagService.findAllByIds(tagIds);

        // regroupement des données
        Map<Long, List<Answer>> answersByQuestionId = answerList.stream().collect(Collectors.groupingBy(a -> a.getQuestion().getId(), Collectors.toList()));
        Map<Long, Question> questionById = questionList.stream().collect(Collectors.toMap(Question::getId, Function.identity()));
        Map<Long, List<Question>> questionsByTestId = thqList.stream().collect(Collectors.groupingBy(thq -> thq.getTest().getId(), Collectors.mapping(thq -> questionById.get(thq.getQuestion().getId()), Collectors.toList())));
        Map<Long, Tag> tagById = tagList.stream().collect(Collectors.toMap(Tag::getId, Function.identity()));
        Map<Long, List<Tag>> tagListByQuestionId = qhtList.stream().collect(Collectors.groupingBy(qht -> qht.getQuestion().getId(), Collectors.mapping(qht -> tagById.get(qht.getTag().getId()), Collectors.toList())));

        // création des dtos
        List<TestExportDto> dtos = UtilMapper.mapTestListToTestExportDtos(testList, questionsByTestId, answersByQuestionId);


        return UtilExcel.exportTestList(dtos);
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

        List<Question> questionList = this.questionService.findAllByIds(questionIds);
        dto.setQuestionList(UtilMapper.mapQuestionListToQuestionSmallDtos(questionList));
        dto.setSuccessRate(UtilEntity.computeSuccessRate(questionList));
        dto.setTimeLimit(UtilEntity.computeTimeLimit(questionList));

        return dto;
    }

    public TakeTestDto findTestToTake(Long id) {
        Test test = this.findById(id);
        TakeTestDto dto = new TakeTestDto(test);

        //récup des questions du tests
        List<TestHasQuestion> thqList = this.thqService.findAllByTestId(id);
        List<Long> questionIds = thqList.stream().map((thq) -> thq.getQuestion().getId()).collect(Collectors.toList());
        List<Question> questions = this.questionService.findAllByIds(questionIds);

        //récup des réponses des questions
        List<Answer> answers = this.answerService.findAllByQuestionIds(questionIds);

        //construction des dtos
        Map<Long, List<Answer>> answersByQuestionId = new HashMap<>();
        for (Question q : questions) {
            Long questionId = q.getId();
            List<Answer> questionAnswers = answers.stream().filter(a -> questionId.equals(a.getQuestion().getId())).collect(Collectors.toList());
            answersByQuestionId.computeIfAbsent(questionId, k -> new ArrayList<>()).addAll(questionAnswers);
        }
        
        List<TakeQuestionDto> questionDtos = new ArrayList<>();
        for (Question q : questions) {
            TakeQuestionDto qDto = new TakeQuestionDto(q);

            List<Answer> questionAnswers = answersByQuestionId.getOrDefault(q.getId(), new ArrayList<>());
            List<AnswerDto> aDtos = UtilMapper.mapAnswerListToAnswerDtos(questionAnswers);
            qDto.setChoices(aDtos);
            questionDtos.add(qDto);
        }

        dto.setQuestionList(questionDtos);
        return dto;
    }

    /** UPDATE **/

    @Transactional
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

    public TestSessionDto submitTestResult(SubmitTestDto dataDto, UserPrincipal user) {

        // récup des réponses et des questions
        List<Long> answerIds = dataDto.getAnswers().stream().filter(SubmitQuestionDto::hasSelectedAnswerIds).flatMap(q -> q.getSelectedAnswerIds().stream()).collect(Collectors.toList());
        List<Long> questionIds = dataDto.getAnswers().stream().map(SubmitQuestionDto::getQuestionId).collect(Collectors.toList());
        Set<Long> questionIdsSet = new HashSet<>(questionIds);

        List<Answer> answerList = this.answerService.findAllByIds(answerIds);
        List<Question> questionList = this.questionService.findAllByIds(questionIds);
        List<Answer> correctAnswerList = this.answerService.findAllCorrectByQuestionIds(questionIds);

        // organisation des données
        Map<Long, Question> questionById = questionList.stream().collect(Collectors.toMap(Question::getId, Function.identity()));
        Map<Long, List<Answer>> userAnswersByQuestionId = answerList.stream().collect(Collectors.groupingBy(a -> a.getQuestion().getId()));
        Map<Long, List<Answer>> correctAnswersByQuestionId = correctAnswerList.stream().collect(Collectors.groupingBy(a -> a.getQuestion().getId()));
      
        // instanciation des listes à créer/modifier
        List<UserHasAnswer> userAnswers = new ArrayList<>();
        List<Question> questionsToUpdate = new ArrayList<>();
        User u = new User(user);

        // sauvegarde de la session
        TestSession session = new TestSession();
        session.setTest(new Test(dataDto.getId()));
        session.setUser(u);
        this.tsService.create(session);

        // inutile?
        Set<Long> questionIdsDone = new HashSet<>();

        int correctQuestionCount = 0;

        for (SubmitQuestionDto qDto : dataDto.getAnswers()) {
            Long qId = qDto.getQuestionId();
                
            if (questionById.containsKey(qId) && correctAnswersByQuestionId.containsKey(qId) && userAnswersByQuestionId.containsKey(qId)) {
                Question currentQuestion = questionById.get(qId);
                List<Answer> currentUserAnswers = userAnswersByQuestionId.get(qId);
                List<Answer> currentCorrectAnswers = correctAnswersByQuestionId.get(qId);

                boolean isAnswerCorrect = currentQuestion.areAnswersCorrect(currentUserAnswers, currentCorrectAnswers);
                if (isAnswerCorrect) correctQuestionCount++;

                // création des réponses de l'utilisateur
                for (Answer uAnswer : currentUserAnswers) {
                    UserHasAnswer uha = new UserHasAnswer(uAnswer);
                    uha.setUser(u);
                    uha.setQuestion(new Question(qDto.getQuestionId()));
                    uha.setSession(session);

                    userAnswers.add(uha);
                }

                // maj des stats de la question
                if (!questionIdsDone.contains(currentQuestion.getId())) {
                    currentQuestion.computeCounts(currentUserAnswers, currentCorrectAnswers);
                    questionsToUpdate.add(currentQuestion);

                    questionIdsDone.add(currentQuestion.getId());
                }
            }
        }

        double sessionSuccessRate = 0.0;
        if (!questionIdsSet.isEmpty()) {
            sessionSuccessRate = ((double) correctQuestionCount / questionIdsSet.size()) * 100;
        }
        session.setSuccessRate(sessionSuccessRate);


        // maj des données en base
        this.tsService.update(session);
        this.uhaService.createMany(userAnswers);
        this.questionService.updateMany(questionsToUpdate);

        return new TestSessionDto(session);
    }

    public List<Test> importTestList(MultipartFile file, UserPrincipal user) {
        try {
            return this.testImportService.importExcel(file.getInputStream(), user);
        } catch(IOException e) {
            throw new RuntimeException("An error occured while importing file", e);
        }
    }

    /** DELETE **/

    @Transactional
    public Integer deleteTest(Long id, UserPrincipal user) {
        if (!UtilAuth.isAdmin(user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot perform this action");

        // récupération des sessions liées au test
        List<TestSession> sessionList = this.tsService.findAllByTestId(id);
        List<Long> sessionIds = sessionList.stream().map(TestSession::getId).collect(Collectors.toList());

        // supprimer les UserHasAnswer
        this.uhaService.deleteAllBySessionIds(sessionIds);

        // supprimer les TestHasQuestion
        this.thqService.deleteAllByTestId(id);

        // suprimer les TestSession
        this.tsService.deleteAllByIds(sessionIds);
        
        // supprimer le Test
        Integer deletedCount = this.testRepository.deleteTestById(id);
        if (deletedCount == 0) throw new NotFoundException("No test found with id " + id);

        return deletedCount;
    }
    
}
