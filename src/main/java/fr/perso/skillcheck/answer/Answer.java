package fr.perso.skillcheck.answer;

import fr.perso.skillcheck.answer.dto.AnswerDto;
import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String  content;

    @Column(nullable = false)
    private Boolean isCorrect;

    public Answer() {}

    public Answer(AnswerDto dto) {
        this.id = dto.getId();
        this.question = new Question(dto.getQuestionId());
        this.content = dto.getContent();
        this.isCorrect = dto.getIsCorrect();
    }

    public Answer(Long id, Long questionId, String content, Boolean isCorrect) {
        this.id = id;
        this.question = new Question(questionId);
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public Answer(Long questionId, String content, Boolean isCorrect) {
        this.question = new Question(questionId);
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

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean hasQuestion() {
        return !UtilEntity.isEmpty(this.question);
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
