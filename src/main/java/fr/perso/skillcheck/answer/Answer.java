package fr.perso.skillcheck.answer;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    @Column(nullable = false)
    private Long    questionId;

    @Column(nullable = false)
    private String  content;

    @Column(nullable = false)
    private Boolean isCorrect;

    public Answer() {}

    public Answer(Long id, Long questionId, String content, Boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public Answer(Long questionId, String content, Boolean isCorrect) {
        this.questionId = questionId;
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
        return !UtilEntity.isEmpty(this.id);
    }

    /** QUESTION ID **/

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public boolean hasQuestionId() {
        return !UtilEntity.isEmpty(this.questionId);
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

    public void isCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean hasIsCorrect() {
        return !UtilEntity.isEmpty(this.isCorrect);
    }

    public boolean isCorrectTrue() {
        return this.hasIsCorrect() && Boolean.TRUE.equals(this.isCorrect);
    }


}
