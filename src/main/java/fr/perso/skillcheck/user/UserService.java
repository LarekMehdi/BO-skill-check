package fr.perso.skillcheck.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.user.dto.UserDto;

@Service
public class UserService {

    @Autowired
    private UserRepository      userRepository;
    
    /** FIND **/

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /** CREATE **/
    
    public User create(UserDto dto) {
        User user = new User(dto);
        user.setDefaultRoleIfNeeded();
        this.userRepository.save(user);

        return user;
    }
}
