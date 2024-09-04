package io.github.julymira.blogue.domain.model.dao;

import io.github.julymira.blogue.domain.model.entity.Comment;
import io.github.julymira.blogue.domain.model.entity.Post;
import io.github.julymira.blogue.domain.model.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CommentDAO {

    @Inject
    EntityManager entityManager;

    public Comment findById(Long id){
        return entityManager.find(Comment.class, id);
    }


    @Transactional
    public void persist(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
        } else {
            entityManager.merge(comment);
        }
    }

    @Transactional
    public void delete(Comment comment) {
        if (!entityManager.contains(comment)) {
            comment = entityManager.merge(comment);
        }
        entityManager.remove(comment);
    }

    public List<Comment> findByUser(User user) {
        TypedQuery<Comment> query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.user = :user", Comment.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public List<Comment> findByPost(Post post) {
        TypedQuery<Comment> query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.post = :post", Comment.class);
        query.setParameter("post", post);
        return query.getResultList();
    }


}
