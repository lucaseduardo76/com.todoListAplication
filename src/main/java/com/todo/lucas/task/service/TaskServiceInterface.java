package com.todo.lucas.task.service;

import com.todo.lucas.task.domain.RequestedNewTaskDTO;
import com.todo.lucas.task.domain.RequestedUpdateTaskDTO;
import com.todo.lucas.task.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskServiceInterface {

    Optional<Task> save(RequestedNewTaskDTO requestedTaskDTO);
    Task findById(String id);
    Task findByTitle(String title);
    Optional<Task> update(RequestedUpdateTaskDTO requestedUpdateTaskDTO);
    List<Task>findAllByUserId(String id);
    void delete(String id);


}
