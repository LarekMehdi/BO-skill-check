package fr.perso.skillcheck.testHasTag;

import fr.perso.skillcheck.tag.Tag;
import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.testHasTag.dto.TestHasTagDto;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TestHasTag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test        test;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag         tag;

    public TestHasTag() {}

    public TestHasTag(Long id, Test test, Tag tag) {
        this.id = id;
        this.test = test;
        this.tag = tag;
    }

    public TestHasTag(Test test, Tag tag) {
        this.test = test;
        this.tag = tag;
    }

    public TestHasTag(TestHasTagDto dto) {
        this.id = dto.getId();
        this.test = new Test(dto.getTestId());
        this.tag = new Tag(dto.getTagId());
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

    /** TEST **/

    public Test getTest() {
        return this.test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public boolean hasTest() {
        return !UtilEntity.isEmpty(this.test);
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
