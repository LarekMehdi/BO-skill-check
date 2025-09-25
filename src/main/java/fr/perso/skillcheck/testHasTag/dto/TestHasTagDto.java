package fr.perso.skillcheck.testHasTag.dto;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotNull;

public class TestHasTagDto {
    
    private Long        id;

    @NotNull( message = "TestId manquant")
    private Long        testId;

    @NotNull( message = "TagId manquant")
    private Long        tagId;

    public TestHasTagDto() {}

    public TestHasTagDto(Long id, Long testId, Long tagId) {
        this.id = id;
        this.testId = testId;
        this.tagId = tagId;
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

    /** TEST ID **/

    public Long getTestId() {
        return this.testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public boolean hasTestId() {
        return !UtilEntity.isEmpty(testId);
    }

    /** TAG ID **/

    public Long getTagId() {
        return this.tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public boolean hasTagId() {
        return !UtilEntity.isEmpty(tagId);
    }
}
