package fr.perso.skillcheck.testSession;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSessionRepository extends JpaRepository<TestSession, Long>{

    /** FIND ALL */

    @Query("SELECT ts FROM TestSession ts WHERE ts.user.id = :userId")
    public List<TestSession> findAllByUserId(@Param("userId") Long userId);
    
}
