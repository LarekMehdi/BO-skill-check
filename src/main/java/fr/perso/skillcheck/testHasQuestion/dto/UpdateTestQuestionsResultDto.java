package fr.perso.skillcheck.testHasQuestion.dto;

import fr.perso.skillcheck.utils.UtilEntity;

public class UpdateTestQuestionsResultDto {
    private Integer    added;
    private Integer    removed;

    public UpdateTestQuestionsResultDto() {}

    public UpdateTestQuestionsResultDto(Integer added, Integer removed) {
        this.added = added;
        this.removed = removed;
    }

    /** ADDED **/

    public Integer getAdded() {
        return this.added;
    }

    public void setAdded(Integer added) {
        this.added = added;
    }

    public boolean hasAdded() {
        return !UtilEntity.isEmpty(this.added);
    }

    /** REMOVED **/

    public Integer getRemoved() {
        return this.removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    public boolean hasRemoved() {
        return !UtilEntity.isEmpty(this.removed);
    }
}
