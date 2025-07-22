package fr.perso.skillcheck.testHasQuestion;

import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class TestHasQuestion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_id", nullable = false)
    private Test        test;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question    question;

    public TestHasQuestion() {}

    public TestHasQuestion(Long id, Test test, Question question) {
        this.id = id;
        this.test = test;
        this.question = question;
    }

    public TestHasQuestion(Test test, Question question) {
        this.test = test;
        this.question = question;
    }

    /** ID **/

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasId() {
        return !UtilEntity.isEmpty(this.id);
    }

    /** TEST **/

    public Test getTest() {
        return this.test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public boolean hasTest() {
        return !UtilEntity.isEmpty(this.test);
    }

    /** QUESTION **/

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean hasQuestion() {
        return !UtilEntity.isEmpty(this.question);
    }
}
