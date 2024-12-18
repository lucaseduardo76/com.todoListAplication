package com.todo.lucas.user.domain;


public class AuthenticationUserDTO {
    private String email;
    private String password;

    public AuthenticationUserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthenticationUserDTO() {
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
