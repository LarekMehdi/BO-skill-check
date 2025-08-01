package fr.perso.skillcheck.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    /** FIND ALL **/

    @Query("SELECT u FROM User u WHERE u.pseudo = :pseudo")
    Optional<User> findByPseudo(@Param("pseudo") String pseudo);

    /** COUNT **/

    boolean existsByEmail(String email);
    
    boolean existsByPseudo(String pseudo);  
    
}
