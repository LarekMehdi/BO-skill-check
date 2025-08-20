package fr.perso.skillcheck.test;

import fr.perso.skillcheck.test.dto.TestDto;
import fr.perso.skillcheck.test.dto.TestExportDto;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @Column(nullable = false)
    private String      title;

    @Column(nullable = false)
    private String      description;

    @Column(nullable = false)
    private Long        createdBy;

    public Test() {}

    public Test(Long id, String title, String description, Long createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
    }

    public Test(String title, String description, Long createdBy) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
    }

    public Test(TestDto dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.createdBy = dto.getCreatedBy();
    }

    public Test(Long id) {
        this.id = id;
    }

    public Test(TestExportDto dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.createdBy = dto.getCreatedBy();
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

    /** TITLE **/

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasTitle() {
        return !UtilEntity.isEmpty(this.title);
    }

    /** DESCRIPTION **/

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasDescription() {
        return !UtilEntity.isEmpty(this.description);
    }

    /** CREATED BY **/

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public boolean hasCreatedBy() {
        return !UtilEntity.isEmpty(this.createdBy);
    }
}
