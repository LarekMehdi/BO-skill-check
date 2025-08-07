package fr.perso.skillcheck.userHasAnswer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHasAnswerRepository extends JpaRepository<UserHasAnswer, Long> {

    /** FIND AL **/

    @Query("SELECT uha FROM UserHasAnswer uha WHERE uha.session.id = :sessionId")
    public List<UserHasAnswer> findAllBySessionId(@Param("sessionId") Long sessionId);
    
}
