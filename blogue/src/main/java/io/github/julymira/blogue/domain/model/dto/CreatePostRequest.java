package io.github.julymira.blogue.domain.model.dto;

public class CreatePostRequest {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
