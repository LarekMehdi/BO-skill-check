package fr.perso.skillcheck.testHasTag;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHasTagRepository extends JpaRepository<TestHasTag, Long>{

    /** FIND ALL **/

    @Query("SELECT tht FROM TestHasTag tht WHERE tht.test.id IN :testIds")
    public List<TestHasTag> findAllByTestIds(@Param("testIds") List<Long> testIds);

    @Query("SELECT tht FROM TestHasTag tht WHERE tht.test.id = :testId")
    public List<TestHasTag> findAllByTestId(@Param("testId") Long testId);

    @Query("SELECT COUNT(tht) FROM TestHasTag tht WHERE tht.test.id = :testId AND tht.tag.id = :tagId")
    public Integer countByTestIdAndTagId(@Param("testId") Long testId, @Param("tagId") Long tagId);

    /** DELETE **/

    @Modifying( clearAutomatically = true)
    @Query("DELETE FROM TestHasTag tht WHERE tht.test.id = :testId AND tht.tag.id = :tagId")
    public Integer deleteByTestIdAndTagId(@Param("testId") Long testId, @Param("tagId") Long tagId);
    
}
