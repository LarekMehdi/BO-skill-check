package fr.perso.skillcheck.test.dto;

import java.util.List;

import fr.perso.skillcheck.question.dto.QuestionExportDto;
import fr.perso.skillcheck.utils.UtilEntity;

public class TestExportDto {
    private Long                        id;
    private String                      title;
    private String                      description;
    private Long                        createdBy;
    private List<QuestionExportDto>     questions;

    public TestExportDto() {}

    public TestExportDto(Long id, String title, String description, Long createdBy, List<QuestionExportDto> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.questions = questions;
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

    /** QUESTIONS **/

    public List<QuestionExportDto> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<QuestionExportDto> questions) {
        this.questions = questions;
    }

    public boolean hasQuestions() {
        return !UtilEntity.isEmpty(questions);
    }
}
