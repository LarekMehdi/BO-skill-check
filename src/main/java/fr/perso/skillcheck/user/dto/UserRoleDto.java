package fr.perso.skillcheck.user.dto;

import fr.perso.skillcheck.constants.Role;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotNull;

public class UserRoleDto {

    @NotNull(message = "Id is missing") 
    private Long        id;

    @NotNull(message = "Role is missing") 
    private Role        role;

    public UserRoleDto() {}

    public UserRoleDto(Long id, Role role) {
        this.id = id;
        this.role = role;
    }

    /** ID **/

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasId() {
        return !UtilEntity.isEmpty(this.id);
    }

    /** ROLE **/

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean hasRole() {
        return !UtilEntity.isEmpty(this.role);
    }
}
