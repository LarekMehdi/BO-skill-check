package fr.perso.skillcheck.question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    @Query("SELECT q FROM Question q WHERE q.content LIKE %:keyword%")
    List<Question> findAllByContent(@Param("keyword") String keyword);

    @Query("SELECT q FROM Question q WHERE q.id = :id")
    Optional<Question> findQuestionById(@Param("id") Long id);
}
