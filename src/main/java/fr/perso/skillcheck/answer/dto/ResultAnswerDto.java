package fr.perso.skillcheck.answer.dto;

import fr.perso.skillcheck.answer.Answer;
import fr.perso.skillcheck.utils.UtilEntity;

public class ResultAnswerDto {
    private Long        id;
    private String      content;
    private Boolean     isCorrect;
    private Boolean     isSelectedByUser;

    public ResultAnswerDto() {}

    public ResultAnswerDto(Long id, String content, Boolean isCorrect, Boolean isSelectedByUser) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.isSelectedByUser = isSelectedByUser;
    }

    public ResultAnswerDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.isCorrect = answer.getIsCorrect();
        this.isSelectedByUser = false;
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

    /** CONTENT **/

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean hasContent() {
        return !UtilEntity.isEmpty(this.content);
    }

    /** IS CORRECT **/

    public Boolean getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean hasIsCorrect() {
        return !UtilEntity.isEmpty(this.isCorrect);
    }

    public boolean isCorrectTrue() {
        return this.hasIsCorrect() && Boolean.TRUE.equals(this.isCorrect);
    }

    /** IS SELECTED BY USER **/

    public Boolean getIsSelectedByUser() {
        return this.isSelectedByUser;
    }

    public void setIsSelectedByUser(Boolean isSelectedByUser) {
        this.isSelectedByUser = isSelectedByUser;
    }

    public boolean hasIsSelectedByUser() {
        return !UtilEntity.isEmpty(this.isSelectedByUser);
    }

    public boolean isSelectedByUserTrue() {
        return this.hasIsSelectedByUser() && Boolean.TRUE.equals(this.isSelectedByUser);
    }

}
