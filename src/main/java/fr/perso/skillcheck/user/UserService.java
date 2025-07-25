package fr.perso.skillcheck.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository      userRepository;
    
    /** FIND **/

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
    
}
