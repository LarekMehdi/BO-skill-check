package fr.perso.skillcheck.userHasAnswer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHasAnswerService {

    @Autowired
    private UserHasAnswerRepository     uhaRepository;

    /** FIND ALL **/

    public List<UserHasAnswer> findAllBySessionId(Long sessionId) {
        return this.uhaRepository.findAllBySessionId(sessionId);
    }
    
    /** CREATE **/

    public List<UserHasAnswer> createMany(List<UserHasAnswer> uhaList) {
        return this.uhaRepository.saveAll(uhaList);
    }

    /** DELETE **/

    public Integer deleteAllBySessionIds(List<Long> sessionIds) {
        return this.uhaRepository.deleteAllBySessionIds(sessionIds);
    }

    public Integer deleteAllByQuestionId(Long questionId) {
        return this.uhaRepository.deleteAllByQuestionId(questionId);
    }

    public Integer deleteAllByQuestionIds(List<Long> questionIds) {
        return this.uhaRepository.deleteAllByQuestionIds(questionIds);
    }

    public Integer deleteAllByAnswerIds(List<Long> answerIds) {
        return this.uhaRepository.deleteAllByAnswerIds(answerIds);
    }
}
