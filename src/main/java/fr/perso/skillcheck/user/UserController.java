package fr.perso.skillcheck.user;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.security.annotations.CurrentUser;
import fr.perso.skillcheck.user.dto.SmallUserDto;
import fr.perso.skillcheck.user.dto.UserDetailsDto;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.PageDto;
import jakarta.validation.Valid;

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

    /** FIND ALL **/

    @GetMapping()
    public PageDto<SmallUserDto> findAllWithPagination(@ModelAttribute @Valid GenericFilter filter) {
        return this.userService.findAllWithPagination(filter);
    }


   
}
