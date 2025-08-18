package fr.perso.skillcheck.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.test.TestService;
import fr.perso.skillcheck.testSession.TestSession;
import fr.perso.skillcheck.testSession.TestSessionService;
import fr.perso.skillcheck.testSession.dto.UserTestSessionDto;
import fr.perso.skillcheck.user.dto.SmallUserDto;
import fr.perso.skillcheck.user.dto.UserDetailsDto;
import fr.perso.skillcheck.user.dto.UserRoleDto;
import fr.perso.skillcheck.utils.GenericFilter;
import fr.perso.skillcheck.utils.PageDto;
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
        return this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with id " + id));
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
        testById = tests.stream().collect(Collectors.toMap(Test::getId, Function.identity()));

        // construction des dtos
        List<UserTestSessionDto> sessionDtos = UtilMapper.mapTestSessionListToUserTestSessionDtos(sessions, testById);
        dto.setSessionList(sessionDtos);

        return dto;
    }

    /** FIND ALL **/
    
    public PageDto<SmallUserDto> findAllWithPagination(GenericFilter filter) {
        filter.initGenericFilterIfNeeded();
        Pageable pageable = filter.toPageable();
        Page<User> users= this.userRepository.findAll(pageable);

        List<User> userList = users.stream().collect(Collectors.toList());
        List<SmallUserDto> dtos = UtilMapper.mapUserListToSmallUserDtos(userList);

        PageDto<SmallUserDto> result= new PageDto<>(dtos, users.getTotalElements());

        return result;
    }

    /** UPDATE **/

    public User changeUserRole(UserRoleDto dto, Long id, UserPrincipal user) {
        if (!UtilAuth.isAdmin(user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You're not allowed to modify this ressource");
        if (!Objects.equals(dto.getId(), id)) throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Id mismatch");
        
        User u = this.findById(dto.getId());
        u.setRole(dto.getRole());
        this.userRepository.save(u);
        return u;
    }
    
}
