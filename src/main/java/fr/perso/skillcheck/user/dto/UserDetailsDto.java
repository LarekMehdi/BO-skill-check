package fr.perso.skillcheck.user.dto;

import java.util.List;

import fr.perso.skillcheck.constants.Role;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.testSession.dto.UserTestSessionDto;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.utils.UtilEntity;

public class UserDetailsDto extends UserDto {
    private List<UserTestSessionDto>    sessionList;

    public UserDetailsDto() {}

    public UserDetailsDto(Long id, String pseudo, String email, String password, Role role, List<UserTestSessionDto> sessionList) {
        super(id, pseudo, email, password, role);
        this.sessionList = sessionList;
    }

    public UserDetailsDto(Long id, String pseudo, String email, String password, Role role) {
        super(id, pseudo, email, password, role);
    }

    public UserDetailsDto(UserPrincipal user) {
        super(user);
    }

    public UserDetailsDto(User user) {
        super(user);
    }

    /** SESSION LIST **/

    public List<UserTestSessionDto> getSessionList() {
        return this.sessionList;
    }

    public void setSessionList(List<UserTestSessionDto> sessionList) {
        this.sessionList = sessionList;
    }

    public boolean hasSessionList() {
        return !UtilEntity.isEmpty(this.sessionList);
    }
}
