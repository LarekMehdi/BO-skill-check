package fr.perso.skillcheck.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.AnswerRepository;
import fr.perso.skillcheck.question.dto.QuestionDtoWithTagIds;
import fr.perso.skillcheck.question.dto.QuestionDtoWithTags;
import fr.perso.skillcheck.questionHasTag.QuestionHasTag;
import fr.perso.skillcheck.questionHasTag.QuestionHasTagService;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.tag.Tag;
import fr.perso.skillcheck.tag.TagService;
import fr.perso.skillcheck.tag.dto.TagDto;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.UtilList;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository              questionRepository;

    @Autowired
    private AnswerRepository                answerRepository;

    @Autowired
    private QuestionHasTagService           qhtService;

    @Autowired
    private TagService                      tagService;

    /** FIND ALL **/

    public List<QuestionDtoWithTags> findAll(GenericFilter filter) {
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
        return dtos;
    }

    /** FIND **/

    public Question findById(Long id) {
        return this.questionRepository.findById(id).orElse(null);
    }

    /** CREATE **/
    
    public Question create(QuestionDtoWithTagIds dto, UserPrincipal user) {
        Question question = new Question(dto);
        question.setCreatedBy(user.getId());

        this.questionRepository.save(question);

        List<Answer> answers = dto.getAnswersEntitiesWithQuestionId(question.getId());
        this.answerRepository.saveAll(answers);

        List<QuestionHasTag> tags = new ArrayList<>();
        for (Long tagId : dto.getTagIds()) {
            QuestionHasTag tag = new QuestionHasTag(new Question(question.getId()), new Tag(tagId));
            tags.add(tag);
        }


        this.qhtService.createMany(tags);

        return question;
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
