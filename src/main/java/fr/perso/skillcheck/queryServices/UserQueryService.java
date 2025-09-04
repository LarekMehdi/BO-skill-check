package fr.perso.skillcheck.queryServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.user.UserRepository;

@Service
public class UserQueryService {

    @Autowired
    private UserRepository         userRepository;

    /** FIND **/

    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with id " + id));
    } 
    
}
