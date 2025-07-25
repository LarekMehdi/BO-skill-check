package fr.perso.skillcheck.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.auth.dto.SigninDto;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.user.dto.UserDto;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService     authService;
    
    /** SIGNUP **/

    @PostMapping("signup")
    public User signup(@RequestBody @Valid UserDto dto) {
        return this.authService.signup(dto);
    }

    /** SIGNIN **/

    @PostMapping("signin")
    public User signin(@RequestBody @Valid SigninDto dto) {
        try {
            return this.authService.signin(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
