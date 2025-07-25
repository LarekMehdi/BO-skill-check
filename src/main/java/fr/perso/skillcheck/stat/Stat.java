package fr.perso.skillcheck.stat;

import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Stat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User        user;

    @Column(nullable = false)
    private Integer     totalTestsCompleted;

    @Column(nullable = false)
    private Integer     totalQuestionsAnswered;

    @Column(nullable = false)
    private Double      successRate;

    @Column(nullable = false)
    private Integer     timePassed;

    public Stat() {}

    public Stat(Long id, User user, Integer totalTestsCompleted, Integer totalQuestionsAnswered, Double successRate, Integer timePassed) {
        this.id = id;
        this.user = user;
        this.totalTestsCompleted = totalTestsCompleted;
        this.totalQuestionsAnswered = totalQuestionsAnswered;
        this.successRate = successRate;
        this.timePassed = timePassed;
    }

    public Stat(User user, Integer totalTestsCompleted, Integer totalQuestionsAnswered, Double successRate, Integer timePassed) {
        this.user = user;
        this.totalTestsCompleted = totalTestsCompleted;
        this.totalQuestionsAnswered = totalQuestionsAnswered;
        this.successRate = successRate;
        this.timePassed = timePassed;
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

    /** TOTAL TESTS COMPLETED **/

    public Integer getTotalTestsCompleted() {
        return this.totalTestsCompleted;
    }

    public void setTotalTestsCompleted(Integer totalTestsCompleted) {
        this.totalTestsCompleted = totalTestsCompleted;
    }

    public boolean hasTotalTestsCompleted() {
        return !UtilEntity.isEmpty(this.totalTestsCompleted);
    }

    /** TOTAL QUESTIONS ANSWERED **/

    public Integer getTotalQuestionsAnswered() {
        return this.totalQuestionsAnswered;
    }

    public void setTotalQuestionsAnswered(Integer totalQuestionsAnswered) {
        this.totalQuestionsAnswered = totalQuestionsAnswered;
    }

    public boolean hasTotalQuestionsAnswered() {
        return !UtilEntity.isEmpty(this.totalQuestionsAnswered);
    }

    /** SUCCESS RATE **/

    public Double getSuccessRate() {
        return this.successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    public boolean hasSuccessRate() {
        return !UtilEntity.isEmpty(this.successRate);
    }

    /** TIME PASSED **/

    public Integer getTimePassed() {
        return this.timePassed;
    }

    public void setTimePassed(Integer timePassed) {
        this.timePassed = timePassed;
    }

    public boolean hasTimePassed() {
        return !UtilEntity.isEmpty(this.timePassed);
    }
}
