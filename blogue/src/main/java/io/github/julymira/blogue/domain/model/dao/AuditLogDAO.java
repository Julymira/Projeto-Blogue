package io.github.julymira.blogue.domain.model.dao;

import io.github.julymira.blogue.domain.model.entity.AuditLog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class AuditLogDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveLog(AuditLog log) {
        entityManager.persist(log);
    }
}