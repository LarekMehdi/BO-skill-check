package fr.perso.skillcheck.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.security.annotations.CurrentUser;
import fr.perso.skillcheck.test.dto.SubmitTestDto;
import fr.perso.skillcheck.test.dto.TakeTestDto;
import fr.perso.skillcheck.test.dto.TestDetailsDto;
import fr.perso.skillcheck.test.dto.TestDto;
import fr.perso.skillcheck.test.dto.UpdateTestQuestionDto;
import fr.perso.skillcheck.test.filter.TestFilter;
import fr.perso.skillcheck.testHasQuestion.dto.UpdateTestQuestionsResultDto;
import fr.perso.skillcheck.testSession.dto.TestSessionDto;
import fr.perso.skillcheck.utils.GenericFilter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestService testService;

    /** FIND ALL **/

    @GetMapping()
    public Page<Test> findAllWithPagination(@ModelAttribute @Valid GenericFilter filter) {
        return this.testService.findAllWithPagination(filter);
    }

    @GetMapping("/export") 
    public byte[] exportTestList(@ModelAttribute @Valid TestFilter filter, @CurrentUser UserPrincipal user) {
        //renvoyer une ResponseEntity avec le nom du fichier?
        return this.testService.exportTestList(filter, user);
    }

    /** FIND **/

    @GetMapping("/{id}")
    public TestDetailsDto findDtoById(@PathVariable("id") Long id) {
        return this.testService.findDtoById(id);
    }

    @GetMapping("/{id}/takeTest")
    public TakeTestDto findTestToTake(@PathVariable("id") Long id) {
        return this.testService.findTestToTake(id);
    }

    /** UPDATE **/

    @PutMapping("/questions")
    public UpdateTestQuestionsResultDto updateQuestions(@RequestBody @Valid UpdateTestQuestionDto dto) {
        return this.testService.updateQuestions(dto);
    }

    /** CREATE **/

    @PostMapping()
    public Test create(@RequestBody @Valid TestDto testDto, @CurrentUser UserPrincipal user) {
        return this.testService.create(testDto, user);
    }

    @PostMapping("/{id}/submit")
    public TestSessionDto submitTestResult(@RequestBody @Valid SubmitTestDto dataDto, @CurrentUser UserPrincipal user) {
        return this.testService.submitTestResult(dataDto, user);
    }

    @PostMapping("/import")
    public ResponseEntity<List<Test>> importTestList(@RequestParam("file") MultipartFile file, @CurrentUser UserPrincipal user) {
        List<Test> tests = this.testService.importTestList(file, user);
        return ResponseEntity.ok(tests);
    }

    /** DELETE **/

    @DeleteMapping("/{id}")
    public Integer deleteTest(@PathVariable("id") Long id, @CurrentUser UserPrincipal user) {
        return this.testService.deleteTest(id, user);
    }
}
