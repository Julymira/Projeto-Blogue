package io.github.julymira.blogue.domain.model.bo;

import io.github.julymira.blogue.domain.model.dao.CommentDAO;
import io.github.julymira.blogue.domain.model.entity.Comment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CommentBO {

    @Inject
    CommentDAO commentDAO;

    public void saveComment(Comment comment) {
        validateComment(comment);
        commentDAO.persist(comment);
    }

    private void validateComment(Comment comment) {

        if(comment.getComment_text() == null){
            throw new IllegalArgumentException("O comentário não pode ser nulo.");
        } else if (comment.getComment_text().length() > 500) {
            throw new IllegalArgumentException("O comentário deve ter no máximo 500 caracteres.");
        }

    }

    public CommentDAO getCommentDAO(){
        return commentDAO;
    }


}
