package fr.perso.skillcheck.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.security.annotations.CurrentUser;
import fr.perso.skillcheck.test.dto.TestDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tests")
public class TestController {
    
    @Autowired
    private TestService     testService;

    /** CREATE **/

    @PostMapping()
    public Test create(@RequestBody @Valid TestDto testDto, @CurrentUser UserPrincipal user) {
        return this.testService.create(testDto, user);
    }
}
