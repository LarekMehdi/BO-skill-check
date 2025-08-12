package fr.perso.skillcheck.user.dto;

import fr.perso.skillcheck.constants.Role;
import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UserDto {
    
    private Long    id;

    @NotBlank(message = "Le pseudo ne doit pas être vide") 
    @Size(min = 2, max = 50, message = "Le pseudo doit contenir entre 2 et 50 caractères")
    private String  pseudo;

    @NotBlank(message = "L'email ne doit pas être vide")
    @Email(message = "L'email doit être valide")
    private String  email;

    @NotBlank(message = "Le password ne doit pas être vide")
    @Size(min = 6, message = "Le password doit contenir au moins 6 caractères")
    private String  password;

    private Role    role = Role.USER;

    public UserDto() {}

    public UserDto(Long id, String pseudo, String email, String password, Role role) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDto(String pseudo, String email, String password, Role role) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDto(String pseudo, String email, String password) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }

    public UserDto(UserPrincipal user) {
        this.id = user.getId();
        this.pseudo = user.getUsername();
        this.role = user.getAuthorities().stream().findFirst().map(auth -> Role.valueOf(auth.getAuthority())).orElse(null);
    }

    public UserDto(User user) {
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

    /** PASSWORD **/

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasPassword() {
        return !UtilEntity.isEmpty(this.password);
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
