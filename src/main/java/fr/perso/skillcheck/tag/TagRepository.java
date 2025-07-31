package fr.perso.skillcheck.tag;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    
    /** UPDATE **/

    @Modifying
    @Query("UPDATE Tag t set t.label = :label WHERE t.id = :id")
    public Tag update(@Param("id") Long id, @Param("label") String label);
}
