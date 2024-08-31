package io.github.julymira.blogue.domain.model.dto;

import io.github.julymira.blogue.domain.model.entity.Post;

import java.time.LocalDateTime;

public class PostResponse {
    private Long id;
    private String text;
    private LocalDateTime dateTime;
    private String imageUrl;
    private String title;
    private Long userId;

    public PostResponse(Long id, String text, LocalDateTime dateTime, String imageUrl, String title, Long userId) {
        this.id = id;
        this.text = text;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        this.title = title;
        this.userId = userId;
    }

    public static PostResponse fromEntity(Post post) {
        return new PostResponse(
                post.getId(),
                post.getText(),
                post.getDateTime(),
                post.getImageUrl(),
                post.getTitle(),
                post.getUser().getId()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
