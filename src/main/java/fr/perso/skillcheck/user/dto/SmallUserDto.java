package fr.perso.skillcheck.user.dto;

import fr.perso.skillcheck.constants.Role;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.utils.UtilEntity;

public class SmallUserDto {
    
    private Long        id;
    private String      pseudo;
    private String      email;
    private Role        role;

    public SmallUserDto() {}

    public SmallUserDto(Long id, String pseudo, String email, Role role) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.role = role;
    }

    public SmallUserDto(User user) {
        this.id = user.getId();
        this.pseudo = user.getPseudo();
        this.email = user.getEmail();
        this.role = user.getRole();
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

    /** PSEUDO **/

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public boolean hasPseudo() {
        return !UtilEntity.isEmpty(this.pseudo);
    }

    /** EMAIL **/

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean hasEmail() {
        return !UtilEntity.isEmpty(this.email);
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
