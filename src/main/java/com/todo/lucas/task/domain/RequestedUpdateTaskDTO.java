package com.todo.lucas.task.domain;

import java.time.LocalDate;
import java.util.Objects;

public class RequestedUpdateTaskDTO {

    String id;
    String title;
    String description;
    LocalDate initialDate;
    LocalDate endDate;
    StatusRole status;

    public RequestedUpdateTaskDTO(String id, String title, String description,
                                  LocalDate initialDate, LocalDate endDate, StatusRole statusRole) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.status = statusRole;
    }

    public RequestedUpdateTaskDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public StatusRole getStatus() {
        return status;
    }

    public void setStatus(StatusRole status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        RequestedUpdateTaskDTO that = (RequestedUpdateTaskDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(initialDate, that.initialDate) && Objects.equals(endDate, that.endDate) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, initialDate, endDate, status);
    }
}
