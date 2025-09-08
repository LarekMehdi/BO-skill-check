package fr.perso.skillcheck.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.AnswerService;
import fr.perso.skillcheck.answer.dto.SmallAnswerDto;
import fr.perso.skillcheck.exceptions.NotFoundException;
import fr.perso.skillcheck.queryServices.TestQueryService;
import fr.perso.skillcheck.queryServices.UserQueryService;
import fr.perso.skillcheck.question.dto.QuestionDetailsDto;
import fr.perso.skillcheck.question.dto.QuestionDtoWithTagIds;
import fr.perso.skillcheck.question.dto.QuestionDtoWithTags;
import fr.perso.skillcheck.questionHasTag.QuestionHasTag;
import fr.perso.skillcheck.questionHasTag.QuestionHasTagService;
import fr.perso.skillcheck.questionHasTag.dto.QuestionHasTagDto;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.tag.Tag;
import fr.perso.skillcheck.tag.TagService;
import fr.perso.skillcheck.tag.dto.TagDto;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.test.dto.SmallTestDto;
import fr.perso.skillcheck.testHasQuestion.TestHasQuestion;
import fr.perso.skillcheck.testHasQuestion.TestHasQuestionService;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.user.dto.SmallUserDto;
import fr.perso.skillcheck.userHasAnswer.UserHasAnswerService;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.PageDto;
import fr.perso.skillcheck.utils.UtilAuth;
import fr.perso.skillcheck.utils.UtilList;
import fr.perso.skillcheck.utils.UtilMapper;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository              questionRepository;

    @Autowired
    private AnswerService                   answerService;

    @Autowired
    private QuestionHasTagService           qhtService;

    @Autowired
    private TagService                      tagService;

    @Autowired
    private UserHasAnswerService            uhaService;

    @Autowired
    private TestHasQuestionService          thqService;

    @Autowired
    private UserQueryService                userQueryService;

    @Autowired
    private TestQueryService                testQueryService;

    /** FIND ALL **/

    public PageDto<QuestionDtoWithTags> findAll(GenericFilter filter) {
        filter.initGenericFilterIfNeeded();
        Pageable pageable = filter.toPageable();
        Page<Question> questions = this.questionRepository.findAll(pageable);

        List<Long> questionIds =  UtilList.collect(questions.toList(), Question::getId);
        List<QuestionHasTag> qhtList = qhtService.findAllByQuestionIds(questionIds);

        List<Long> tagIds = UtilList.collect(qhtList, qht -> qht.getTag().getId());
        List<Tag> tags = this.tagService.findAllByIds(tagIds);

        Map<Long, List<Long>> tagsByQuestionId = new HashMap<>();
        for (QuestionHasTag qht : qhtList) {
            Long questionId = qht.getQuestion().getId();
            Long tagId = qht.getTag().getId();

            tagsByQuestionId.computeIfAbsent(questionId, k -> new ArrayList<>()).add(tagId);
        }

        List<QuestionDtoWithTags> dtos = this.__mapQuestionToDtosWithTags(questions.toList(), tags, tagsByQuestionId);

        PageDto<QuestionDtoWithTags> result = new PageDto<>(dtos, questions.getTotalElements());
        return result;
    }

    public List<Question> findAllByIds(List<Long> ids) {
        return this.questionRepository.findAllByIds(ids);
    }

    /** FIND **/

    public QuestionDetailsDto findDetailsById(Long id) {
        
        // récupération de la question
        Question question = this.questionRepository.findById(id).orElse(null);
        if (question == null) throw new NotFoundException("No question found with id " + id);

        // récupération du créateur de la question
        User creator = this.userQueryService.findById(question.getCreatedBy());
        SmallUserDto createdBy = new SmallUserDto(creator);

        // récupération des tests
        List<TestHasQuestion> thqList = this.thqService.findAllByQuestionId(id);
        List<Long> testIds = thqList.stream().map(thq -> thq.getTest().getId()).collect(Collectors.toList());
        List<Test> tests = this.testQueryService.findAllByIds(testIds);
        List<SmallTestDto> testList = UtilMapper.mapTestListToSmallTestDtos(tests);

        // récupération des answers
        List<Answer> answers = this.answerService.findAllByQuestionId(id);
        List<SmallAnswerDto> answerList = UtilMapper.mapAnswerListToSmallAnswerDtos(answers);

        // récupération des tags
        List<QuestionHasTag> qhtList = this.qhtService.findAllByQuestionId(id);
        List<Long> tagIds = qhtList.stream().map(qht -> qht.getTag().getId()).collect(Collectors.toList());
        List<Tag> tags = this.tagService.findAllByIds(tagIds);
        List<TagDto> tagList = UtilMapper.mapTagListToTagDtos(tags); 

        // construction du dto
        QuestionDetailsDto dto = new QuestionDetailsDto(question, createdBy, testList, answerList, tagList);
        return dto;
    }

    /** UPDATE **/

    public List<Question> updateMany(List<Question> questionList) {
        return this.questionRepository.saveAll(questionList);
    }

    /** CREATE **/
    
    public Question create(QuestionDtoWithTagIds dto, UserPrincipal user) {
        Question question = new Question(dto);
        if (dto.getAnswers().size() > 4 || dto.getAnswers().size() < 2) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Wrong amount of answers");
        }
        question.computeIsMultipleAnswer(dto.getAnswers());
        question.setCreatedBy(user.getId());

        this.questionRepository.save(question);

        List<Answer> answers = dto.getAnswersEntitiesWithQuestionId(question.getId());
        this.answerService.createMany(answers);

        List<QuestionHasTag> tags = new ArrayList<>();
        for (Long tagId : dto.getTagIds()) {
            QuestionHasTag tag = new QuestionHasTag(new Question(question.getId()), new Tag(tagId));
            tags.add(tag);
        }

        this.qhtService.createMany(tags);

        return question;
    }

    public List<Question> createMany(List<Question> questionList) {
        return this.questionRepository.saveAll(questionList);
    }

    /** UPDATE **/

    @Transactional
    public QuestionHasTag addTagToQuestion(QuestionHasTagDto dto, UserPrincipal user) {
        if (!UtilAuth.isAdmin(user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot perform this action");
        if (!this.questionRepository.existsById(dto.getQuestionId())) throw new NotFoundException("No question found with id " + dto.getQuestionId());

        int exist = this.qhtService.countByQuestionIdTagId(dto.getQuestionId(), dto.getTagId());
        if (exist > 0) throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "This tag is already linked to this question [questionId:" + dto.getQuestionId() + " , tagId:" + dto.getTagId() + "]");
        QuestionHasTag qht = new QuestionHasTag(dto);
        return this.qhtService.create(qht);
    }

    /** DELETE **/

    @Transactional
    public boolean delete(Long id, UserPrincipal user) {
        if (!UtilAuth.isAdmin(user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot perform this action");
        if (!this.questionRepository.existsById(id)) throw new NotFoundException("No question found with id " + id);

        // userHasAnswer
        this.uhaService.deleteAllByQuestionId(id);

        // questionHastag
        this.qhtService.deleteAllByQuestionId(id);

        // answer
        this.answerService.deleteAllByQuestionId(id);

        // testHasQuestion
        this.thqService.deleteAllByQuestionId(id);

        // question
        int questionDeletedCount = this.questionRepository.deleteQuestionById(id);
        boolean deleted = questionDeletedCount > 0;
        if (!deleted) throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "An error occured while deleting question " + id);

        return deleted;
    }

    /** PRIVATE **/

    private List<QuestionDtoWithTags> __mapQuestionToDtosWithTags(List<Question> questions, List<Tag> tagList, Map<Long, List<Long>> tagsByQuestionId) {
        List<QuestionDtoWithTags> dtos = new ArrayList<>();
        for (Question q : questions) {
            QuestionDtoWithTags dto = new QuestionDtoWithTags(q);
            dto.setAnswers(new ArrayList<>());

            List<TagDto> tagDtos = new ArrayList<>();
            if (tagsByQuestionId.containsKey(q.getId())) {
                List<Long> tagIds = tagsByQuestionId.get(q.getId());
                List<Tag> tags = tagList.stream().filter(tag -> tagIds.contains(tag.getId())).collect(Collectors.toList());
                tagDtos = this.__mapTagsToDtos(tags);
            }
            dto.setTagList(tagDtos);

            dtos.add(dto);
        }
        return dtos;
    }   

    private List<TagDto> __mapTagsToDtos(List<Tag> tags) {
        List<TagDto> dtos = new ArrayList<>();
        for (Tag t : tags) {
            TagDto dto = new TagDto(t);
            dtos.add(dto);
        }
        return dtos;
    }

    
}
