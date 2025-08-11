package fr.perso.skillcheck.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (!UtilEntity.isMyId(id, user)) return null;
 

        UserDetailsDto dto = new UserDetailsDto();
        return dto;
    }
    
}
