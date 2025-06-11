package com.example.soteria.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @NotNull
    @Column(nullable = false)
    private Long userId;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime creationTimestamp;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime expirationTimestamp;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public LocalDateTime getExpirationTimestamp() {
        return expirationTimestamp;
    }

    public void setExpirationTimestamp(LocalDateTime expirationTimestamp) {
        this.expirationTimestamp = expirationTimestamp;
    }
}