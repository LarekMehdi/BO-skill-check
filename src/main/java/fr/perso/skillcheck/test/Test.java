package fr.perso.skillcheck.test;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @Column(nullable = false)
    private String      title;

    @Column(nullable = false)
    private String      description;

    @Column(nullable = false)
    private Double      successRate;

    @Column(nullable = false)
    private Integer     timeLimit;

    @Column(nullable = false)
    private Long        createdBy;

    public Test() {}

    public Test(Long id, String title, String description, Double successRate, Integer timeLimit, Long createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.successRate = successRate;
        this.timeLimit = timeLimit;
        this.createdBy = createdBy;
    }

    public Test(String title, String description, Double successRate, Integer timeLimit, Long createdBy) {
        this.title = title;
        this.description = description;
        this.successRate = successRate;
        this.timeLimit = timeLimit;
        this.createdBy = createdBy;
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

    /** TITLE **/

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasTitle() {
        return !UtilEntity.isEmpty(this.title);
    }

    /** DESCRIPTION **/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasDescription() {
        return !UtilEntity.isEmpty(this.description);
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

    /** TIME LIMIT **/

    public Integer getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean hasTimeLimit() {
        return !UtilEntity.isEmpty(this.timeLimit);
    }

    /** CREATED BY **/

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public boolean hasCreatedBy() {
        return !UtilEntity.isEmpty(this.createdBy);
    }
}
