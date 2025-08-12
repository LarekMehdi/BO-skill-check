package fr.perso.skillcheck.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.test.TestService;
import fr.perso.skillcheck.testSession.TestSession;
import fr.perso.skillcheck.testSession.TestSessionService;
import fr.perso.skillcheck.testSession.dto.UserTestSessionDto;
import fr.perso.skillcheck.user.dto.UserDetailsDto;
import fr.perso.skillcheck.utils.UtilAuth;
import fr.perso.skillcheck.utils.UtilMapper;


@Service
public class UserService {

    @Autowired
    private UserRepository      userRepository;

    @Autowired
    private TestSessionService  tsService;

    @Autowired
    private TestService         testService;
    
    /** FIND **/

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public UserDetailsDto findDetailsById(Long id, UserPrincipal currentUser) {
        //TODO: nombre de test passé + successrate moyen + profil created at?
        if (!UtilAuth.isMyId(id, currentUser) && !UtilAuth.isAdmin(currentUser)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You're a not allowed to access this resource");
 
        // récup des infos du profil
        User user = this.findById(id);
        UserDetailsDto dto = new UserDetailsDto(user);

        // récup des sessions et des tests
        List<TestSession> sessions = this.tsService.findAllByUserId(id);
        List<Long> testIds = sessions.stream().map(ts -> ts.getTest().getId()).collect(Collectors.toList());
        List<Test> tests = this.testService.findAllByIds(testIds);

        // mise en ordre des données
        Map<Long, Test> testById = new HashMap<>();
        Map<Long, List<Test>> testsBySessionId = new HashMap<>();
        testById = tests.stream().collect(Collectors.toMap(Test::getId, Function.identity()));

        for (TestSession session : sessions) {
            testsBySessionId.computeIfAbsent(session.getId(), k -> new ArrayList<>())
                .add(testById.get(session.getTest().getId()));
        }

        // construction des dtos
        List<UserTestSessionDto> sessionDtos = UtilMapper.mapTestSessionListToUserTestSessionDtos(sessions, testsBySessionId);
        dto.setSessionList(sessionDtos);

        return dto;
    }
    
}
