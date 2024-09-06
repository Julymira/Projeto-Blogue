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

    public List<Post> listAllP() {
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p", Post.class);
        return query.getResultList();
    }



    public List<Post> listAll(String search) {
        // Monta a query JPQL com o filtro de busca
        String jpql = "SELECT p FROM Post p WHERE 1 = 1";

        // Se houver um parâmetro de busca, adiciona a condição com LIKE
        if (search != null && !search.isEmpty()) {
            jpql += " AND LOWER(p.title) LIKE LOWER(:search)";
        }

        // Cria a consulta
        TypedQuery<Post> query = entityManager.createQuery(jpql, Post.class);

        // Define o parâmetro da busca, se for passado
        if (search != null && !search.isEmpty()) {
            query.setParameter("search", "%" + search + "%");
        }

        // Retorna os resultados
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
