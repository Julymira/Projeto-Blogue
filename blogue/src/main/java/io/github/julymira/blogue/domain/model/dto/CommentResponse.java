package io.github.julymira.blogue.domain.model.dto;

import io.github.julymira.blogue.domain.model.entity.Comment;

public class CommentResponse {

    private Long id;
    private String comment_text;
    private Long postId;
    private Long userId;
    private String userName;

    public CommentResponse(Long id, String comment_text, Long postId, Long userId) {
        this.id = id;
        this.comment_text = comment_text;
        this.postId = postId;
        this.userId = userId;
    }

    public CommentResponse(Long id, String comment_text, Long postId, Long userId, String userName) {
        this.id = id;
        this.comment_text = comment_text;
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
    }

    public static CommentResponse fromEntity(Comment comment){
        return new CommentResponse(
                comment.getId(),
                comment.getComment_text(),
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getUser().getName()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
