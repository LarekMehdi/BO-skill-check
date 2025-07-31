package fr.perso.skillcheck.test.dto;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotBlank;

public class TestDto {

    private Long        id;

    @NotBlank(message = "Le titre ne doit pas être vide")
    private String      title;

    @NotBlank(message = "La description ne doit pas être vide")
    private String      description;

    private Double      successRate;

    private Integer     timeLimit;

    private Long        createdBy;

    public TestDto() {}

    public TestDto(Long id, String title, String description, Double successRate, Integer timeLimit, Long createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.successRate = successRate;
        this.timeLimit = timeLimit;
        this.createdBy = createdBy;
    }

    public TestDto(String title, String description, Double successRate, Integer timeLimit, Long createdBy) {
        this.title = title;
        this.description = description;
        this.successRate = successRate;
        this.timeLimit = timeLimit;
        this.createdBy = createdBy;
    }

    public TestDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /** ID **/

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasId() {
        return !UtilEntity.isEmpty(id);
    }

    /** TITLE **/

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasTitle() {
        return !UtilEntity.isEmpty(title);
    }

    /** DESCRIPTION **/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasDescription() {
        return !UtilEntity.isEmpty(description);
    }

    /** SUCCESS RATE **/

    public Double getSuccessRate() {
        return this.successRate;
    }

    public void successRate(Double successRate) {
        this.successRate = successRate;
    }

    public boolean hasSuccessRate() {
        return !UtilEntity.isEmpty(successRate);
    }

    /** TIME LIMIT **/

    public Integer getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean hasTimeLimit() {
        return !UtilEntity.isEmpty(timeLimit);
    }

    /** CREATED BY **/

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public boolean hasCreatedBy() {
        return !UtilEntity.isEmpty(createdBy);
    }
}
