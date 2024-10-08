package io.github.julymira.blogue.domain.model.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRegisterDTO {

    @NotBlank(message =  "Name is Required")
    private String name;

    @NotBlank(message =  "Email is Required")
    private String email;

    @NotBlank(message =  "Password is Required")
    private String password;

    public UserRegisterDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
