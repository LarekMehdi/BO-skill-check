package fr.perso.skillcheck.testHasTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHasTagRepository extends JpaRepository<TestHasTag, Long>{
    
}
