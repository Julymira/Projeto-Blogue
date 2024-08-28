package io.github.julymira.blogue.domain.model.dao;

import io.github.julymira.blogue.domain.model.entity.Post;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostDAO implements PanacheRepository<Post> {

    public Post findById(Long id) {
        return find("id", id).firstResult();
    }


}
