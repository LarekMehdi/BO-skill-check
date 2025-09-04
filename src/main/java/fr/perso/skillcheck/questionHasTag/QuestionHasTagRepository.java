package fr.perso.skillcheck.questionHasTag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionHasTagRepository extends JpaRepository<QuestionHasTag, Long>{

    /** FIND ALL **/

    @Query("SELECT qht FROM QuestionHasTag qht WHERE qht.id in :ids")
    List<QuestionHasTag> findAllByIds(@Param("ids") List<Long> ids);

    @Query("SELECT qht FROM QuestionHasTag qht WHERE qht.question.id in :questionIds")
    List<QuestionHasTag> findAllByQuestionIds(@Param("questionIds") List<Long> questionIds);

    @Query("SELECT qht FROM QuestionHasTag qht WHERE qht.question.id = :questionId")
    List<QuestionHasTag> findAllByQuestionId(@Param("questionIds") Long questionId);

    @Query("SELECT qht FROM QuestionHasTag qht WHERE qht.tag.id in :tagIds")
    List<QuestionHasTag> findAllByTagIds(@Param("tagIds") List<Long> tagIds);

    /** DELETE **/

    @Modifying( clearAutomatically = true)
    @Query("DELETE FROM QuestionHasTag qht WHERE qht.question.id = :questionId")
    public Integer deleteAllByQuestionId(@Param("questionId") Long questionId);
    
}
