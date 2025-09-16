package fr.perso.skillcheck.question;

import fr.perso.skillcheck.security.UserPrincipal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.question.dto.QuestionDetailsDto;
import fr.perso.skillcheck.question.dto.QuestionDtoWithTagIds;
import fr.perso.skillcheck.question.dto.QuestionDtoWithTags;
import fr.perso.skillcheck.question.dto.UpdateQuestionDto;
import fr.perso.skillcheck.question.filter.QuestionFilter;
import fr.perso.skillcheck.questionHasTag.QuestionHasTag;
import fr.perso.skillcheck.questionHasTag.dto.QuestionHasTagDto;
import fr.perso.skillcheck.security.annotations.CurrentUser;
import fr.perso.skillcheck.utils.PageDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/questions")
public class QuestionController {

  @Autowired
  private QuestionService    questionService;

  /** FIND **/

  @GetMapping("/{id}")
  public QuestionDetailsDto findDetailsById(@PathVariable("id") Long id) {
    return this.questionService.findDetailsById(id);
  }

  /** FIND ALL **/

  @GetMapping()
  public PageDto<QuestionDtoWithTags> findAll(@ModelAttribute @Valid QuestionFilter filter) {
    return this.questionService.findAll(filter);
  }

  /** CREATE **/

  @PostMapping()
  public Question create(@RequestBody @Valid QuestionDtoWithTagIds question, @CurrentUser UserPrincipal user) {
    return this.questionService.create(question, user);
  }

  /** UPDATE **/

  @PutMapping("/{id}")
  public Question update(@RequestBody @Valid UpdateQuestionDto dto, @PathVariable("id") Long id, @CurrentUser UserPrincipal user) {
    return this.questionService.update(dto, user);
  }

  @PatchMapping("/{id}/tag/add")
  public QuestionHasTag addTagToQuestion(@RequestBody @Valid QuestionHasTagDto dto, @PathVariable("id") Long id, @CurrentUser UserPrincipal user) {
    return this.questionService.addTagToQuestion(dto, user);
  }

  @PatchMapping("{id}/tag/remove")
  public Integer removeTagFromQuestion(@RequestBody @Valid QuestionHasTagDto dto, @PathVariable("id") Long id, @CurrentUser UserPrincipal user) {
    return this.questionService.removeTagFromQuestion(dto, user);
  }

  /** DELETE **/

  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable("id") Long id, @CurrentUser UserPrincipal user) {
    return this.questionService.delete(id, user);
  }

  @DeleteMapping("/delete")
  public boolean deleteAll(@RequestParam("ids") List<Long> ids, @CurrentUser UserPrincipal user) {
    return this.questionService.deleteAll(ids, user);
  }
}
