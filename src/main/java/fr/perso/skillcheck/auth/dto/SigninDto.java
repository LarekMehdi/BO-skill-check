package fr.perso.skillcheck.auth.dto;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.validation.constraints.NotBlank;

public class SigninDto {
    
    @NotBlank(message = "Le pseudo ne doit pas être vide")
    private String pseudo;

    @NotBlank(message = "Le password ne doit pas être vide")
    private String password;

    public SigninDto() {}

    public SigninDto(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
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

    /** PASSWORD **/

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasPassword() {
        return !UtilEntity.isEmpty(this.password);
    }
}
