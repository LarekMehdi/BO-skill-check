package fr.perso.skillcheck.testHasTag;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHasTagRepository extends JpaRepository<TestHasTag, Long>{

    /** FIND ALL **/

    @Query("SELECT tht FROM TestHasTag tht WHERE tht.test.id IN :testIds")
    public List<TestHasTag> findAllByTestIds(@Param("testIds") List<Long> testIds);
    
}
