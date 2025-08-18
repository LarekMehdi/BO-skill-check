package fr.perso.skillcheck.answer.dto;

import fr.perso.skillcheck.utils.UtilEntity;

public class SmallAnswerDto {
    private Long        id;
    private String      content;
    private Boolean     isCorrect;

    public SmallAnswerDto() {}

    public SmallAnswerDto(Long id, String content, Boolean isCorrect) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
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

    /** CONTENT **/

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean hasContent() {
        return !UtilEntity.isEmpty(content);
    }

    /** IS CORRECT **/

    public Boolean getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean isCorrectTrue() {
        return this.hasIsCorrect() && Boolean.TRUE.equals(this.isCorrect);
    }

    public boolean hasIsCorrect() {
        return !UtilEntity.isEmpty(isCorrect);
    }
}
