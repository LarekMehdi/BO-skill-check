package fr.perso.skillcheck.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.perso.skillcheck.auth.dto.AuthResponseDto;
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
    public ResponseEntity<?> signin(@RequestBody @Valid SigninDto dto) {
    try {
        AuthResponseDto response = this.authService.signin(dto);
        return ResponseEntity.ok(response);
    } catch (UsernameNotFoundException | BadCredentialsException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur");
    }
}
}
