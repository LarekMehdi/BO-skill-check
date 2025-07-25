package fr.perso.skillcheck.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.perso.skillcheck.auth.dto.SigninDto;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.user.UserRepository;
import fr.perso.skillcheck.user.dto.UserDto;

@Service
public class AuthService {

    @Autowired
    private UserRepository      userRepository;

    @Autowired
    private PasswordEncoder     passwordEncoder;

    /** SIGNUP **/
    
    public User signup(UserDto dto) {
        User user = new User(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDefaultRoleIfNeeded();
        this.userRepository.save(user);

        return user;
    }

    /** SIGNIN **/

    //TODO: créer des classes d'exception personnalisées
    
    public User signin(SigninDto dto) throws Exception {
        Optional<User> opt = this.userRepository.findByPseudo(dto.getPseudo());
        if (!opt.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with this pseudo");

        User user = opt.get();

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid password");
        }

        return user;
        
    } 
}
