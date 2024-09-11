package io.github.julymira.blogue.domain.model.dto;

import java.time.LocalDateTime;

public class AuditLogDTO {
    private Long id;
    private String action;
    private Long userId;
    private LocalDateTime timestamp;

    public AuditLogDTO(Long id, String action, Long userId, LocalDateTime timestamp) {
        this.id = id;
        this.action = action;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}