package fr.perso.skillcheck.queryServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.perso.skillcheck.exceptions.NotFoundException;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.test.TestRepository;

@Service
public class TestQueryService {
    // Classe permettant de garder une sépartion claire des responsabilités de TestService et TestSessionService 
    // en évitant les dépendances circulaires entre ces deux classes

    @Autowired
    private TestRepository      testRepository;

    /** FIND **/

    public Test findById(Long id) {
        return this.testRepository.findById(id).orElseThrow(() -> new NotFoundException("No test found with id " + id));
    }

    /** CREATE **/

    public List<Test> createMany(List<Test> tests) {
        return this.testRepository.saveAll(tests);
    }

    
}
