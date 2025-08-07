package fr.perso.skillcheck.userHasAnswer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHasAnswerService {

    @Autowired
    private UserHasAnswerRepository     uhaRepository;

    /** FIND ALL **/

    public List<UserHasAnswer> findAllBySessionId(Long sessionId) {
        return this.uhaRepository.findAllBySessionId(sessionId);
    }
    
    /** CREATE **/

    public List<UserHasAnswer> createMany(List<UserHasAnswer> uhaList) {
        return this.uhaRepository.saveAll(uhaList);
    }
}
