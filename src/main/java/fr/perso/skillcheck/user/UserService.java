package fr.perso.skillcheck.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.user.dto.UserDetailsDto;
import fr.perso.skillcheck.utils.UtilEntity;


@Service
public class UserService {

    @Autowired
    private UserRepository      userRepository;
    
    /** FIND **/

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public UserDetailsDto findDetailsById(Long id, UserPrincipal user) {
        //TODO: && !isAdmin()
        //if (!UtilEntity.isMyId(id, user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You're a not allowed to access this resource");
 

        UserDetailsDto dto = new UserDetailsDto();
        return dto;
    }
    
}
