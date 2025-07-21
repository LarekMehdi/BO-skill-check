package fr.perso.skillcheck.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.perso.skillcheck.question.dto.QuestionDto;

public class QuestionService {

    @Autowired
    private QuestionRepository  questionRepository;

    /** FIND ALL **/

    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    /** FIND **/

    public Question findById(Long id) {
        return this.questionRepository.findById(id).orElse(null);
    }

    /** CREATE **/
    
    // TODO: gerer les answers en meme temps
    public Question create(QuestionDto dto) {
        Question question = new Question(dto);
        return this.questionRepository.save(question);
    }

    
}
