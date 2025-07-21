package fr.perso.skillcheck.question;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController {

  private final QuestionRepository questionRepository;

  public QuestionController(QuestionRepository repo) {
    this.questionRepository = repo;
  }

  @GetMapping
  public List<Question> findAll() {
    return questionRepository.findAll();
  }

  @GetMapping("/{id}")
  public Question findById(@PathVariable("id") Long id) {
    return questionRepository.findById(id).orElse(null);
  }

  @PostMapping
  public Question create(@RequestBody Question question) {
    return questionRepository.save(question);
  }
}
