package fr.perso.skillcheck.testHasQuestion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHasQuestionRepository extends JpaRepository<TestHasQuestion, Long> {

    /** FIND ALL **/
    
    @Query("SELECT thq FROM TestHasQuestion thq WHERE thq.test.id = :testId")
    public List<TestHasQuestion> findAllByTestId(@Param("testId") Long testId);

    @Query("SELECT thq FROM TestHasQuestion thq WHERE thq.test.id IN :testIds")
    public List<TestHasQuestion> findAllByTestIds(@Param("testIds") List<Long> testIds);

    /** DELETE **/

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM TestHasQuestion thq WHERE thq.test.id = :testId AND thq.question.id in :questionIds")
    public Integer deleteAllByTestIdAndQuestionIds(@Param("testId") Long testId, @Param("questionIds") List<Long> questionIds);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM TestHasQuestion thq WHERE thq.test.id = :testId")
    public Integer deleteAllByTestId(@Param("testId") Long testId);
}
