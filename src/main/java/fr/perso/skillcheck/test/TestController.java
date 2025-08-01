package fr.perso.skillcheck.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.security.annotations.CurrentUser;
import fr.perso.skillcheck.test.dto.TestDto;
import fr.perso.skillcheck.utils.GenericFilter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tests")
public class TestController {
    
    @Autowired
    private TestService     testService;

    /** FIND ALL **/

    @GetMapping()
    public Page<Test> findAllWithPagination(GenericFilter filter) {
        return this.testService.findAllWithPagination(filter);
    }

    /** FIND **/

    @GetMapping("/{id}")
    public Test findById(@PathVariable("id") Long id) {
        return this.testService.findById(id);
    }

    /** CREATE **/

    @PostMapping()
    public Test create(@RequestBody @Valid TestDto testDto, @CurrentUser UserPrincipal user) {
        return this.testService.create(testDto, user);
    }
}
