package fr.perso.skillcheck.questionHasTag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionHasTagService {

    @Autowired
    private QuestionHasTagRepository    qhtRepository;

    /** FIND ALL **/

    public List<QuestionHasTag> findAllByIds(List<Long> ids) {
        return this.qhtRepository.findAllByIds(ids);
    }

    public List<QuestionHasTag> findAllByQuestionIds(List<Long> questionIds) {
        return this.qhtRepository.findAllByQuestionIds(questionIds);
    }

    public List<QuestionHasTag> findAllByQuestionId(Long questionId) {
        return this.qhtRepository.findAllByQuestionId(questionId);
    }

    public List<QuestionHasTag> findAllByTagIds(List<Long> tagIds) {
        return this.qhtRepository.findAllByTagIds(tagIds);
    }

    public int countByQuestionIdTagId(Long questionId, Long tagId) {
        return this.qhtRepository.countByQuestionIdTagId(questionId, tagId);
    }

    /** CREATE **/

    public QuestionHasTag create(QuestionHasTag tag) {
        return this.qhtRepository.save(tag);
    }

    public List<QuestionHasTag> createMany(List<QuestionHasTag> tags) {
        return this.qhtRepository.saveAll(tags);
    }

    /** DELETE **/
    public Integer deleteAllByQuestionId(Long questionId) {
        return this.qhtRepository.deleteAllByQuestionId(questionId);
    }
    
}
