package fr.perso.skillcheck.testHasQuestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestHasQuestionService {
    
    @Autowired
    private TestHasQuestionRepository   thqRepository;

    /** FIND ALL **/

    public List<TestHasQuestion> findAllByTestId(Long testId) {
        return this.thqRepository.findAllByTestId(testId);
    }

    public List<TestHasQuestion> findAllByTestIds(List<Long> testIds) {
        return this.thqRepository.findAllByTestIds(testIds);
    } 

    public List<TestHasQuestion> findAllByQuestionId(Long questionId) {
        return this.thqRepository.findAllByQuestionId(questionId);
    }

    /** CREATE **/

    public List<TestHasQuestion> createMany(List<TestHasQuestion> thqList) {
        return this.thqRepository.saveAll(thqList);
    }

    /** DELETE **/

    public Integer deleteAllByTestIdAndQuestionIds(Long testId, List<Long> questionIds) {
        return this.thqRepository.deleteAllByTestIdAndQuestionIds(testId, questionIds);
    }

    public Integer deleteAllByTestId(Long testId) {
        return this.thqRepository.deleteAllByTestId(testId);
    }

    public Integer deleteAllByQuestionId(Long questionId) {
        return this.thqRepository.deleteAllByQuestionId(questionId);
    }

    public Integer deleteAllByQuestionIds(List<Long> questionIds) {
        return this.thqRepository.deleteAllByQuestionIds(questionIds);
    }
}
