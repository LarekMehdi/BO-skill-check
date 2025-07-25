package fr.perso.skillcheck.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import fr.perso.skillcheck.security.JwtTokenProvider;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.auth.dto.AuthResponseDto;
import fr.perso.skillcheck.auth.dto.SigninDto;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.user.UserRepository;
import fr.perso.skillcheck.user.dto.UserDto;

@Service
public class AuthService {

    @Autowired
    private UserRepository          userRepository;

    @Autowired
    private PasswordEncoder         passwordEncoder;

    @Autowired
    private AuthenticationManager   authenticationManager;

    @Autowired 
    private JwtTokenProvider        jwtTokenProvider;

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
    
    public AuthResponseDto signin(SigninDto dto) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getPseudo(), dto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        return new AuthResponseDto(
            token,
            principal.getId(),
            principal.getUsername(),
            principal.getAuthorities().stream().findFirst().get().getAuthority()
        );
        
    } 
}
