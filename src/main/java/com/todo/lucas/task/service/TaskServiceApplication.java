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

@Service
public class TaskServiceApplication implements TaskServiceInterface{

    @Autowired
    UserServiceApplication userServiceApplication;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TokenService tokenService;

    @Override
    public Task save(RequestedNewTaskDTO requestedTaskDTO) {
        Task task = buildNewTaskfromRequestTaskDTO(requestedTaskDTO);
        return taskRepository.save(task);
    }

    @Override
    public Task findTaskById(String id) {
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
    public Task update(RequestedUpdateTaskDTO requestedTaskDTO) {
        System.out.println("[INICIO] UPDATE TASK SERVICE");
        Task previusTask = taskRepository.findById(requestedTaskDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Task not found"));

        Task updatedTask = buildUpdatedTaskFromRequestTaskDTO(requestedTaskDTO, previusTask.getUser());
        updatedTask.setId(previusTask.getId());
        this.saveUpdatedTask(updatedTask);
        System.out.println("[FIM] UPDATE TASK SERVICE");
        return updatedTask;
    }

    @Override
    public void delete(String id) {
        taskRepository.deleteById(id);
    }

    private Task buildUpdatedTaskFromRequestTaskDTO(RequestedUpdateTaskDTO requestedUpdateTaskDTO, User user) {

         return new Task(requestedUpdateTaskDTO.getId(), requestedUpdateTaskDTO.getTitle(),
                requestedUpdateTaskDTO.getDescription(), requestedUpdateTaskDTO.getInitialDate(),
                requestedUpdateTaskDTO.getEndDate(), requestedUpdateTaskDTO.getStatus(), user);
    }

    private Task buildNewTaskfromRequestTaskDTO(RequestedNewTaskDTO requestedTaskDTO) {
        User user =  userServiceApplication.findByIdOrElseThrowException(requestedTaskDTO.getUserId());

        Task task = new Task(requestedTaskDTO.getTitle(),requestedTaskDTO.getDescription(),
                requestedTaskDTO.getEndDate(), user, StatusRole.NOT_STARTED);

        return task;
    }

    private Task saveUpdatedTask(Task task) {
        return taskRepository.save(task);
    }
}
