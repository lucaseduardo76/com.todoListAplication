package com.todo.lucas.task.service;

import com.todo.lucas.infra.security.TokenService;
import com.todo.lucas.task.domain.RequestedNewTaskDTO;
import com.todo.lucas.task.domain.RequestedUpdateTaskDTO;
import com.todo.lucas.task.domain.StatusRole;
import com.todo.lucas.task.domain.Task;
import com.todo.lucas.task.repository.TaskRepository;
import com.todo.lucas.user.domain.User;
import com.todo.lucas.user.service.UserServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceApplication implements TaskServiceInterface{

    @Autowired
    UserServiceApplication userServiceApplication;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TokenService tokenService;

    @Override
    public Optional<Task> save(RequestedNewTaskDTO requestedTaskDTO) {
        Task task = buildNewTaskfromRequestTaskDTO(requestedTaskDTO);
        return Optional.of(taskRepository.save(task));
    }

    @Override
    public Task findById(String id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No task found with id: " + id));
    }

    @Override
    public Task findByTitle(String title) {
        return taskRepository.findByTitle(title).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No task found with title " + title));
    }
    @Override
    public List<Task> findAllByUserId(String id) {
        return taskRepository.findAllByUserId(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Task found for this user"));
    }

    @Override
    public Optional<Task> update(RequestedUpdateTaskDTO requestedTaskDTO) {

        Task previusTask = taskRepository.findById(requestedTaskDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Task not found"));

        Task updatedTask = buildUpdatedTaskFromRequestTaskDTO(requestedTaskDTO);
        updatedTask.setId(previusTask.getId());

        return Optional.of(updatedTask);
    }

    @Override
    public void delete(String id) {
        var taskOwner =
    }

    private Task buildUpdatedTaskFromRequestTaskDTO(RequestedUpdateTaskDTO requestedUpdateTaskDTO) {
        User user = userServiceApplication.findByIdOrElseThrowException(requestedUpdateTaskDTO.getId());

         return new Task(requestedUpdateTaskDTO.getId(), requestedUpdateTaskDTO.getTitle(),
                requestedUpdateTaskDTO.getDescription(), requestedUpdateTaskDTO.getInitialDate(),
                requestedUpdateTaskDTO.getEndDate(), requestedUpdateTaskDTO.getStatusRole(), user);
    }

    private Task buildNewTaskfromRequestTaskDTO(RequestedNewTaskDTO requestedTaskDTO) {
        User user =  userServiceApplication.findByIdOrElseThrowException(requestedTaskDTO.getUserId());

        Task task = new Task(requestedTaskDTO.getTitle(),requestedTaskDTO.getDescription(),
                requestedTaskDTO.getEndDate(), user, StatusRole.NOT_STARTED);

        return task;
    }
}
