package io.github.julymira.blogue.domain.model.dao;

import io.github.julymira.blogue.domain.model.entity.Post;
import io.github.julymira.blogue.domain.model.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PostDAO {

    @Inject
    EntityManager entityManager;

    public Post findById(Long id) {
        return entityManager.find(Post.class, id);
    }

    public List<Post> listAll() {
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p", Post.class);
        return query.getResultList();
    }

    @Transactional
    public void persist(Post post) {
        if (post.getId() == null) {
            entityManager.persist(post);
        } else {
            entityManager.merge(post);
        }
    }

    @Transactional
    public void delete(Post post) {
        if (!entityManager.contains(post)) {
            post = entityManager.merge(post);
        }
        entityManager.remove(post);
    }

    public List<Post> findByUser(User user) {
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p WHERE p.user = :user", Post.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

}
