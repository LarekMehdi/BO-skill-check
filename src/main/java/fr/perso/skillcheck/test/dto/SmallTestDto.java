package fr.perso.skillcheck.test.dto;

import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.utils.UtilEntity;

public class SmallTestDto {
    private Long        id;
    private String      title;

    public SmallTestDto() {}

    public SmallTestDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public SmallTestDto(Test test) {
        this.id = test.getId();
        this.title = test.getTitle();
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
}
