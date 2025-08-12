package fr.perso.skillcheck.test;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{

    /** FIND ALL **/

    @Deprecated
    @Query(value = "SELECT t FROM Test t LIMIT :limit OFFSET :offset", nativeQuery = true)
    public List<Test> findAllWithFilter(@Param("limit") Integer limit, @Param("offset") Integer offset);

    @Query("SELECT t from Test t")
    public Page<Test> findAllWithPagination(Pageable pageable);

    @Query("SELECT t FROM Test t WHERE t.id IN (:ids)")
    public List<Test> findAllByIds(@Param("ids") List<Long> ids);
    
    
}
