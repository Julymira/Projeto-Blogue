package io.github.julymira.blogue.domain.model.dto;

public class CreateCommentRequest {

    private String comment_text;

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }
}
