package fr.perso.skillcheck.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.question.dto.QuestionDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/questions")
public class QuestionController {

  @Autowired
  private QuestionService    questionService;

  /** FIND **/

  @GetMapping("/{id}")
  public Question findById(@PathVariable("id") Long id) {
    return questionService.findById(id);
  }

  /** FIND ALL **/

  @GetMapping
  public List<Question> findAll() {
    return questionService.findAll();
  }

  /** CREATE **/

  @PostMapping
  public Question create(@RequestBody @Valid QuestionDto question) {
    return questionService.create(question);
  }
}
