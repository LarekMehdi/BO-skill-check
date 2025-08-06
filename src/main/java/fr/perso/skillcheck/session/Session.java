package fr.perso.skillcheck.session;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import fr.perso.skillcheck.test.Test;
import fr.perso.skillcheck.user.User;
import fr.perso.skillcheck.utils.UtilEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long            id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_id", nullable = false)
    private Test            test;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User            user;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime   createdAt;

    public Session() {}

    public Session(Long id, Test test, User user, LocalDateTime createdAt) {
        this.id = id;
        this.test = test;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Session(Test test, User user, LocalDateTime createdAt) {
        this.test = test;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Session(Test test, User user) {
        this.test = test;
        this.user = user;
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

    /** USER **/

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean hasUser() {
        return !UtilEntity.isEmpty(this.user);
    }

    /** CREATED AT **/

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean hasCreatedAt() {
        return !UtilEntity.isEmpty(this.createdAt);
    }
}
