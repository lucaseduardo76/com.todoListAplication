package com.todo.lucas.user.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class RegisterDTO {
    @NotEmpty(message = "Email cannot be empty/null")
    private String email;
    @NotEmpty(message = "Password cannot be empty/null")
    @Size(min = 3, max = 255)
    private String password;
    @NotEmpty(message = "Name cannot be empty/null")
    private String name;
    private String phone;
    @NotEmpty(message = "CPF cannot be empty/null")
    private String cpf;

    public RegisterDTO() {
    }

    public RegisterDTO(String email, String password, String name, String phone, String cpf) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RegisterDTO that = (RegisterDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, cpf);
    }
}
