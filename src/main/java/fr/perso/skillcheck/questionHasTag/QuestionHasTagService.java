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

    /** CREATE **/

    public List<QuestionHasTag> createMany(List<QuestionHasTag> tags) {
        return this.qhtRepository.saveAll(tags);
    }

    /** DELETE **/
    public Integer deleteAllByQuestionId(Long questionId) {
        return this.qhtRepository.deleteAllByQuestionId(questionId);
    }
    
}
