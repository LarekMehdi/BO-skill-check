package fr.perso.skillcheck.test.dto;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateTestDto {
    
    @NotNull(message = "l'id est manquant")
    private Long        id;

    @NotBlank(message = "La description ne doit pas être vide")
    private String      description;

    public UpdateTestDto() {}

    public UpdateTestDto(Long id, String description) {
        this.id = id;
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
        return !UtilEntity.isEmpty(this.id);
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
}
