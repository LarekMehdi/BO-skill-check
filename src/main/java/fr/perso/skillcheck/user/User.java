package fr.perso.skillcheck.user;

import fr.perso.skillcheck.constants.Role;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    @Column(nullable = false)
    private String  pseudo;

    @Column(nullable = false)
    private String  email;

    @Column(nullable = false)
    private String  password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role    role = Role.USER;

    public User() {}

    public User(Long id, String pseudo, String email, String password) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
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
