package fr.perso.skillcheck.auth.dto;

//TODO: faire des setters propres

public class AuthResponseDto {
    private String  accessToken;
    private String  tokenType = "Bearer";
    private Long    userId;
    private String  pseudo;
    private String  role;

    public AuthResponseDto(String accessToken, Long userId, String pseudo, String role) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.pseudo = pseudo;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getRole() {
        return role;
    }
}
