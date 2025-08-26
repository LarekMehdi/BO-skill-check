package fr.perso.skillcheck.user.dto;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserProfileDto {
    
    @NotNull(message = "Id is missing") 
    private Long        id;

    @NotBlank(message = "Pseudo is missing") 
    private String      pseudo;

    @NotBlank(message = "Email is missing") 
    private String      email;

    public UserProfileDto() {}

    public UserProfileDto(Long id, String pseudo, String email) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
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

    /** PSEUDO **/

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public boolean hasPseudo() {
        return !UtilEntity.isEmpty(this.pseudo);
    }

    /** EMAIL **/

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean hasEmail() {
        return !UtilEntity.isEmpty(this.email);
    }
}
