package io.github.julymira.blogue.domain.model.bo;

import io.github.julymira.blogue.domain.model.dao.PostDAO;
import io.github.julymira.blogue.domain.model.entity.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PostBO {

    @Inject
    PostDAO postDAO;

    public void savePost(Post post) {
        validatePost(post);
        postDAO.persist(post);
    }

    private void validatePost(Post post) {

        if(post.getText() == null){
            throw new IllegalArgumentException("O texto não pode ser nula.");
        } else if (post.getText().length() > 1000) {
            throw new IllegalArgumentException("O texto deve ter no máximo 255 caracteres.");
        }

        if (post.getImageUrl() == null) {
            throw new IllegalArgumentException("A URL da imagem não pode ser nula.");
        } else if (post.getImageUrl().length() > 255) {
            throw new IllegalArgumentException("A URL da imagem deve ter no máximo 255 caracteres.");
        }

        if(post.getTitle() == null){
            throw new IllegalArgumentException("O título não pode ser nula.");
        } else if (post.getTitle().length() > 150) {
            throw new IllegalArgumentException("O título deve ter no máximo 255 caracteres.");
        }

    }

    public PostDAO getPostDAO() {
        return postDAO;
    }

    public List<Post> listAll(String tituloFiltro) {
        return postDAO.listAll(tituloFiltro);
    }


}
