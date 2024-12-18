package com.todo.lucas.user.domain;

import java.util.Objects;

public class LoginResponseUserDTO {
    private String token;

    public LoginResponseUserDTO(String token) {
        this.token = token;
    }

    public LoginResponseUserDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoginResponseUserDTO that = (LoginResponseUserDTO) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(token);
    }

    @Override
    public String toString() {
        return  "token: " + token;
    }
}
