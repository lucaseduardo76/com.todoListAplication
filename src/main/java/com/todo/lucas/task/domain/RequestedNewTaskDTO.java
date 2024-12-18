package com.todo.lucas.task.domain;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class RequestedNewTaskDTO {

    @NotNull(message = "Title cannot be empty")
    private String title;
    private String description;
    @NotNull(message = "endDate cannot be empty")
    private LocalDate endDate;
    @NotNull(message = "user id cannot be empty")
    private String userId;

    public RequestedNewTaskDTO() {
    }

    public RequestedNewTaskDTO(String title, String description, LocalDate endDate, String userId) {
        this.title = title;
        this.description = description;
        this.endDate = endDate;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
