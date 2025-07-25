package fr.perso.skillcheck.tag;

import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    @Column(nullable = false)
    private String  label;

    
    public Tag() {}

    public Tag(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public Tag(String label) {
        this.label = label;
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

    /** LABEL **/

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean hasLabel() {
        return !UtilEntity.isEmpty(this.label);
    }

    
}
