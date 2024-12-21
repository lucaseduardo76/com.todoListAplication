package com.todo.lucas.user.domain;

import com.todo.lucas.task.domain.Task;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserResponseDTO {


    private String id;

    @Email
    private String email;

    @Size(min = 8, max = 255, message = "The password must have min 8 character and max 255")
    private String password;

    @Size(min = 3, max = 30, message = "The name must have min 3 character and max 30")
    private String name;
    private String phone;
    private String cpf;

    private List<Task> tasks;



}
