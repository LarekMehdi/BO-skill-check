package fr.perso.skillcheck.userHasAnswer;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserHasAnswer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User        user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_id", nullable = true)
    private Test        test;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question    question;

    @ManyToOne(optional = false)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer      answer;

    @Column(nullable = false)
    private Boolean     isCorrect;

    @Column(nullable = true)
    private Integer     responseTime;

    public UserHasAnswer() {}

    public UserHasAnswer(Long id, User user, Question question, Answer answer, Boolean isCorrect, Integer responseTime) {
        this.id = id;
        this.user = user;
        this.question = question;
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.responseTime = responseTime;
    }

    public UserHasAnswer(User user, Question question, Answer answer, Boolean isCorrect, Integer responseTime) {
        this.user = user;
        this.question = question;
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.responseTime = responseTime;
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

    /** USER **/

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean hasUser() {
        return !UtilEntity.isEmpty(this.user);
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

    /** ANSWER **/

    public Answer getAnswer() {
        return this.answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public boolean hasAnswer() {
        return !UtilEntity.isEmpty(this.answer);
    }

    /** IS CORRECT **/

    public Boolean getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean hasIsCorrect() {
        return !UtilEntity.isEmpty(this.isCorrect);
    }

    public boolean isCoorectTrue() {
        return this.hasIsCorrect() && Boolean.TRUE.equals(this.isCorrect);
    }

    /** RESPONSE TIME **/

    public Integer getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    public boolean hasResponseTime() {
        return !UtilEntity.isEmpty(this.responseTime);
    }
       
}
