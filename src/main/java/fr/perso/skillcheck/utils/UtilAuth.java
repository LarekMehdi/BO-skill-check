package fr.perso.skillcheck.utils;

import java.util.Objects;

import fr.perso.skillcheck.constants.Role;
import fr.perso.skillcheck.security.UserPrincipal;

public abstract class UtilAuth {
    

    /** ID **/

    public static boolean isMyId(Long id, UserPrincipal user) {
        if (Objects.equals(id, user.getId())) return true;
        return false;
    }

    /** ROLE **/

    public static boolean isAdmin(UserPrincipal user) {
        return user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(Role.ADMIN.name()));
    }
}
