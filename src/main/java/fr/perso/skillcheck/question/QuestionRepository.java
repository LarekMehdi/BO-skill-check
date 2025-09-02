package fr.perso.skillcheck.question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    /** FIND ALL **/
    
    @Query("SELECT q FROM Question q WHERE q.content LIKE %:keyword%")
    List<Question> findAllByContent(@Param("keyword") String keyword);

    @Query("SELECT q FROM Question q WHERE id IN :ids")
    List<Question> findAllByIds(@Param("ids") List<Long> ids);

    /** FIND **/

    @Query("SELECT q FROM Question q WHERE q.id = :id")
    Optional<Question> findQuestionById(@Param("id") Long id);

    /** DELETE **/

    @Modifying
    @Query("DELETE FROM Question q WHERE q.id = :id")
    public Integer deleteQuestionById(@Param("id") Long id);
}
