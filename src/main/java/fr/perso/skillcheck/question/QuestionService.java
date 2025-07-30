package fr.perso.skillcheck.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.AnswerRepository;
import fr.perso.skillcheck.question.dto.QuestionDto;
import fr.perso.skillcheck.security.UserPrincipal;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository  questionRepository;

    @Autowired
    private AnswerRepository    answerRepository;

    /** FIND ALL **/

    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    /** FIND **/

    public Question findById(Long id) {
        return this.questionRepository.findById(id).orElse(null);
    }

    /** CREATE **/
    
    public Question create(QuestionDto dto, UserPrincipal user) {
        Question question = new Question(dto);
        question.setCreatedBy(user.getId());

        this.questionRepository.save(question);

        List<Answer> answers = dto.getAnswersEntitiesWithQuestionId(question.getId());
        this.answerRepository.saveAll(answers);

        return question;
    }

    
}
