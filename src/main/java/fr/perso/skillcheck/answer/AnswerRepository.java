package fr.perso.skillcheck.answer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Long>  {

    /** FIND ALL **/

    @Query("SELECT a FROM Answer a WHERE a.id IN :ids")
    public List<Answer> findAllByIds(@Param("ids") List<Long> ids);

    @Query("SELECT a FROM Answer a WHERE a.question.id = :questionId")
    public List<Answer> findAllByQuestionId(@Param("id") Long id);

    @Query("SELECT a FROM Answer a WHERE a.question.id in :questionIds")
    public List<Answer> findAllByQuestionIds(@Param("questionIds") List<Long> questionIds);

    @Query("SELECT a FROM Answer a WHERE a.isCorrect = TRUE AND a.question.id in :questionIds")
    public List<Answer> findAllCorrectByQuestionIds(@Param("questionIds") List<Long> questionIds);

    /** DELETE **/

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Answer a WHERE a.question.id = :questionId")
    public Integer deleteAllByQuestionId(@Param("questionId") Long questionId);
    
}
