package io.github.julymira.blogue.domain.model.bo;

import io.github.julymira.blogue.domain.model.dao.AuditLogDAO;
import io.github.julymira.blogue.domain.model.entity.AuditLog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;


@ApplicationScoped
public class AuditLogBO {

    @Inject
    private AuditLogDAO auditLogDAO;

    public void logAction(String action, Long userId) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setUserId(userId);
        log.setTimestamp(LocalDateTime.now());
        auditLogDAO.saveLog(log);
    }
}