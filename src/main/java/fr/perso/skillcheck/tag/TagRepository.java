package fr.perso.skillcheck.tag;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

    /** FIND ALL **/

    @Query("SELECT t FROM Tag t WHERE id in :ids")
    public List<Tag> findAllByIds(@Param("ids") List<Long> ids);

    @Query("SELECT t FROM Tag t WHERE LOWER(t.label) IN LOWER(:labels)")
    public List<Tag> findAllByLabels(@Param("labels") List<String> labels);

    /** FIND **/

    @Query("SELECT t FROM Tag t WHERE LOWER(t.label) = LOWER(:label)")
    public Tag findByLabel(@Param("label") String label);
    
    /** UPDATE **/

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Tag t set t.label = :label WHERE t.id = :id")
    public int update(@Param("id") Long id, @Param("label") String label);
}
