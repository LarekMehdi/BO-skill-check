package fr.perso.skillcheck.auth.dto;

//TODO: faire des setters propres

public class AuthResponseDto {
    private String  accessToken;
    private String  tokenType = "Bearer";
    private Long    id;
    private String  pseudo;
    private String  role;

    public AuthResponseDto(String accessToken, Long id, String pseudo, String role) {
        this.accessToken = accessToken;
        this.id = id;
        this.pseudo = pseudo;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getRole() {
        return role;
    }
}
