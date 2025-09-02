package fr.perso.skillcheck.question;

import fr.perso.skillcheck.security.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.perso.skillcheck.question.dto.QuestionDtoWithTagIds;
import fr.perso.skillcheck.question.dto.QuestionDtoWithTags;
import fr.perso.skillcheck.security.annotations.CurrentUser;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.PageDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/questions")
public class QuestionController {

  @Autowired
  private QuestionService    questionService;

  /** FIND **/

  @GetMapping("/{id}")
  public Question findById(@PathVariable("id") Long id) {
    return this.questionService.findById(id);
  }

  /** FIND ALL **/

  @GetMapping()
  public PageDto<QuestionDtoWithTags> findAll(@ModelAttribute @Valid GenericFilter filter) {
    return this.questionService.findAll(filter);
  }

  /** CREATE **/

  @PostMapping()
  public Question create(@RequestBody @Valid QuestionDtoWithTagIds question, @CurrentUser UserPrincipal user) {
    return this.questionService.create(question, user);
  }

  /** DELETE **/

  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable("id") Long id, @CurrentUser UserPrincipal user) {
    return this.questionService.delete(id, user);
  }
}
