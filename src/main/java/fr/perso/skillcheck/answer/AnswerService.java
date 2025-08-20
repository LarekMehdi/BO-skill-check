package fr.perso.skillcheck.answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository        answerRepository;

    /** FIND ALL **/

    public List<Answer> findAllByIds(List<Long> ids) {
        return this.answerRepository.findAllByIds(ids);
    }

    public List<Answer> findAllByQuestionIds(List<Long> questionIds) {
        return this.answerRepository.findAllByQuestionIds(questionIds);
    }

    public List<Answer> findAllCorrectByQuestionIds(List<Long> questionIds) {
        return this.answerRepository.findAllCorrectByQuestionIds(questionIds);
    }

    /** CREATE **/

    public List<Answer> createMany(List<Answer> answers) {
        return this.answerRepository.saveAll(answers);
    }
    
}
