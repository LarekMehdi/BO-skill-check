package fr.perso.skillcheck.answer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Long>  {

    /** FIND ALL **/

    @Query("SELECT a FROM Answer a WHERE a.id IN :ids")
    public List<Answer> findAllByIds(@Param("ids") List<Long> ids);

    @Query("SELECT a FROM Answer a WHERE a.question.id in :questionIds")
    public List<Answer> findAllByQuestionIds(@Param("questionIds") List<Long> questionIds);
    
}
