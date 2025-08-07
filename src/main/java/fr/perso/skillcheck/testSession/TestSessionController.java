package fr.perso.skillcheck.testSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.security.annotations.CurrentUser;
import fr.perso.skillcheck.testSession.dto.TestSessionDetailsDto;

@RestController
@RequestMapping("/sessions")
public class TestSessionController {

    @Autowired
    private TestSessionService      tsService;

    /** FIND **/

    @GetMapping("/{id}")
    public TestSessionDetailsDto findTestSessionDetails(@PathVariable("id") Long id, @CurrentUser UserPrincipal user) {
        return this.tsService.findTestSessionDetails(id, user);
    }
    
}
