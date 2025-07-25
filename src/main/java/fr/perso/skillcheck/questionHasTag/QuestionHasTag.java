package fr.perso.skillcheck.questionHasTag;

import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.tag.Tag;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class QuestionHasTag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question    question;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag         tag;

    public QuestionHasTag() {}

    public QuestionHasTag(Long id, Question question, Tag tag) {
        this.id = id;
        this.question = question;
        this.tag = tag;
    }

    public QuestionHasTag(Question question, Tag tag) {
        this.question = question;
        this.tag = tag;
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

    /** QUESTION **/

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean hasQuestion() {
        return !UtilEntity.isEmpty(this.question);
    }

    /** TAG **/

    public Tag getTag() {
        return this.tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public boolean hasTag() {
        return !UtilEntity.isEmpty(this.tag);
    }
}
