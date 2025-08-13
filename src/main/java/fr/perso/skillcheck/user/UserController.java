package fr.perso.skillcheck.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.security.annotations.CurrentUser;
import fr.perso.skillcheck.user.dto.UserDetailsDto;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService     userService;
    
    /** FIND **/

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return this.userService.findById(id);
    }

    @GetMapping("/details/{id}")
    public UserDetailsDto findDetailsById(@PathVariable("id") Long id, @CurrentUser UserPrincipal user) {
        return this.userService.findDetailsById(id, user);
    }


   
}
