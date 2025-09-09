package fr.perso.skillcheck.answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.userHasAnswer.UserHasAnswerService;
import fr.perso.skillcheck.utils.UtilAuth;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository        answerRepository;

    @Autowired
    private UserHasAnswerService    uhaService;

    /** FIND ALL **/

    public List<Answer> findAllByIds(List<Long> ids) {
        return this.answerRepository.findAllByIds(ids);
    }

    public List<Answer> findAllByQuestionId(Long questionId) {
        return this.answerRepository.findAllByQuestionId(questionId);
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

    /** DELETE **/

    @Transactional
    public Integer deleteAllByQuestionId(Long questionId) {
        return this.answerRepository.deleteAllByQuestionId(questionId);
    }

    @Transactional
    public Integer deleteAllByIds(List<Long> ids,  UserPrincipal user) {
        if (!UtilAuth.isAdmin(user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot perform this action");
        
        // UserHasAnswer
        this.uhaService.deleteAllByAnswerIds(ids);

        // Answer
        return this.answerRepository.deleteAllByIds(ids);
    }
    
}
