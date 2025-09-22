package fr.perso.skillcheck.testHasTag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestHasTagService {

    @Autowired
    private TestHasTagRepository        thtRepository;

    /** FIND ALL **/

    public List<TestHasTag> findAllByTestIds(List<Long> testIds) {
        return this.thtRepository.findAllByTestIds(testIds);
    }
    
}
