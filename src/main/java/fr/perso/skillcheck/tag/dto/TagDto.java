package fr.perso.skillcheck.tag.dto;

import fr.perso.skillcheck.tag.Tag;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotBlank;

public class TagDto {

    private Long id;

    @NotBlank(message = "Le label ne doit pas être vide")
    private String  label;

    public TagDto() {}

    public TagDto(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public TagDto(String label) {
        this.label = label;
    }

    public TagDto(Tag tag) {
        this.id = tag.getId();
        this.label = tag.getLabel();
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

    /** LABEL **/

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean hasLabel() {
        return !UtilEntity.isEmpty(label);
    }
}
