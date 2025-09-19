package fr.perso.skillcheck.test;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>, JpaSpecificationExecutor<Test>{

    /** FIND ALL **/

    //TODO prendre en compte le filter
    @Query(value = "SELECT t FROM Test t")
    public List<Test> findAllWithFilter();

    @Query("SELECT t FROM Test t WHERE t.id IN (:ids)")
    public List<Test> findAllByIds(@Param("ids") List<Long> ids);

    /** DELETE **/

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Test t WHERE t.id = :id")
    public Integer deleteTestById(@Param("id") Long id);
    
    
}
