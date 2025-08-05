package fr.perso.skillcheck.userHasAnswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHasAnswerRepository extends JpaRepository<UserHasAnswer, Long> {
    
}
