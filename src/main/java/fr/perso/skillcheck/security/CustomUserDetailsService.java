package fr.perso.skillcheck.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.user.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id " + id));

        return UserPrincipal.create(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPseudo(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with pseudo " + username));
        return UserPrincipal.create(user);
    }
    
}
