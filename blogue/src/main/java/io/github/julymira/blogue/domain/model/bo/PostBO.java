package io.github.julymira.blogue.domain.model.bo;

import io.github.julymira.blogue.domain.model.dao.PostDAO;
import io.github.julymira.blogue.domain.model.entity.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PostBO {

    @Inject
    PostDAO postDAO;

    public void savePost(Post post) {
        validatePost(post);
        postDAO.persist(post);
    }

    private void validatePost(Post post) {
        if (post.getText() == null || post.getText().length() > 1000) {
            throw new IllegalArgumentException("O texto do post não pode ser nulo e deve ter no máximo 1000 caracteres.");
        }
        if (post.getImageUrl() != null && post.getImageUrl().length() > 255) {
            throw new IllegalArgumentException("A URL da imagem deve ter no máximo 255 caracteres.");
        }
    }

    public PostDAO getPostDAO() {
        return postDAO;
    }

}
