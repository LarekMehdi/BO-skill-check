package fr.perso.skillcheck.testHasTag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.perso.skillcheck.testHasTag.dto.TestHasTagDto;

@Service
public class TestHasTagService {

    @Autowired
    private TestHasTagRepository        thtRepository;

    /** FIND ALL **/

    public List<TestHasTag> findAllByTestIds(List<Long> testIds) {
        return this.thtRepository.findAllByTestIds(testIds);
    }

    public List<TestHasTag> findAllByTestId(Long testId) {
        return this.thtRepository.findAllByTestId(testId);
    }

    /** CREATE **/

    public TestHasTag create(TestHasTag tht) {
        int exist = this.thtRepository.countByTestIdAndTagId(tht.getTest().getId(), tht.getTag().getId());
        if (exist > 0) throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "This test is already linked to this tag [ testId: " + tht.getTest().getId() + ", tagId: " + tht.getTag().getId() + "]");
        return this.thtRepository.save(tht);
    }

    /** DELETE **/

    public Integer deleteByTestIdAndTagId(TestHasTagDto dto) {
        return this.thtRepository.deleteByTestIdAndTagId(dto.getTestId(), dto.getTagId());
    }
    
}
