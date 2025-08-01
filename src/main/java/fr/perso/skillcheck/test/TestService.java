package fr.perso.skillcheck.test;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.security.UserPrincipal;
import fr.perso.skillcheck.test.dto.TestDto;
import fr.perso.skillcheck.utils.GenericFilter;

@Service
public class TestService {

    @Autowired
    private TestRepository      testRepository;

    /** FIND ALL **/

    public Page<Test> findAllWithPagination(GenericFilter filter) {
        filter.initGenericFilterIfNeeded();
        Pageable pageable = filter.toPageable();
        return this.testRepository.findAllWithPagination(pageable);
    }

    /** CREATE **/

    public Test create(TestDto dto, UserPrincipal user) {
        Test test = new Test(dto);
        test.setCreatedBy(user.getId());
        test.setSuccessRate(0.0);
        test.setTimeLimit(0);
        return this.testRepository.save(test);
    }
    
}
