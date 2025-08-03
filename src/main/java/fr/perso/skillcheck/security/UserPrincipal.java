package fr.perso.skillcheck.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.utils.UtilEntity;

public class UserPrincipal implements UserDetails{

    private Long id;
    private String pseudo;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal() {}

    public UserPrincipal(Long id, String pseudo, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.authorities = authorities;
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

    @Override
    public String getUsername() {
        return this.pseudo;
    }

    public void setpseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public boolean hasPseudo() {
        return !UtilEntity.isEmpty(this.pseudo);
    }

    /** PASSWORD **/

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasPassword() {
        return !UtilEntity.isEmpty(this.password);
    }

    /** AUTHORITIES **/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean hasAuthorities() {
        return !UtilEntity.isEmpty(this.authorities);
    }

    /** METHODS **/
    
    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority(user.getRole().name())
        );

        return new UserPrincipal(
            user.getId(),
            user.getPseudo(),
            user.getPassword(),
            authorities);
    }

}
