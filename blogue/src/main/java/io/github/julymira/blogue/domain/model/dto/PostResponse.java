package io.github.julymira.blogue.domain.model.dto;

import io.github.julymira.blogue.domain.model.entity.Post;

import java.time.LocalDateTime;

public class PostResponse {
    private String text;
    private LocalDateTime dateTime;

    private String imageUrl;

    public static PostResponse fromEntity(Post post){
        var response = new PostResponse();
        response.setText(post.getText());
        response.setDateTime(post.getDateTime());
        response.setImageUrl(post.getImageUrl());
        return response;
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
}
