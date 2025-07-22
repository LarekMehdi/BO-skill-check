package fr.perso.skillcheck.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.answer.AnswerRepository;
import fr.perso.skillcheck.question.dto.QuestionDto;

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
    
    public Question create(QuestionDto dto) {
        Question question = new Question(dto);
        this.questionRepository.save(question);

        List<Answer> answers = dto.getAnswersEntitiesWithQuestionId(question.getId());
        this.answerRepository.saveAll(answers);

        return question;
    }

    
}
