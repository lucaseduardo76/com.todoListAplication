package com.todo.lucas.task.controller.aplicattion;


import com.auth0.jwt.JWT;
import com.todo.lucas.infra.security.TokenService;
import com.todo.lucas.task.controller.inter.TaskControllerInter;
import com.todo.lucas.task.domain.RequestedNewTaskDTO;
import com.todo.lucas.task.domain.RequestedUpdateTaskDTO;
import com.todo.lucas.task.domain.Task;
import com.todo.lucas.task.repository.TaskRepository;
import com.todo.lucas.task.service.TaskServiceApplication;
import com.todo.lucas.user.service.UserServiceApplication;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("task")
public class TaskControllerController implements TaskControllerInter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private TaskServiceApplication taskServiceApplication;
    @Autowired
    private UserServiceApplication userServiceApplication;

    @Override
    public ResponseEntity<List<Task>> getAllTasks(String token, String idUser) {
        System.out.println("[INICIO] getAllTasks");

       var idUserValidated = validateTokenWithUserId(token, idUser);
       if (idUserValidated == null)
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        var allTasks = taskServiceApplication.findAllByUserId(idUser);

        System.out.println("[FIM] getAllTasks");
        return ResponseEntity.ok().body(allTasks);
    }

    @Override
    public ResponseEntity<Task> getTaskById(String token, String idUser, String idTask) {
        if (validateTokenWithUserId(token, idUser) == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok().body(taskServiceApplication.findTaskById(idTask));
    }

    @Override
    public ResponseEntity<Task> createTask(String token, RequestedNewTaskDTO requestedNewTaskDTO, String idUser) {
        if (validateTokenWithUserId(token, idUser) == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        var newTask = taskServiceApplication.save(requestedNewTaskDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newTask.getId())
                .toUri();

        return ResponseEntity.created(uri).body(newTask);
    }

    @Override
    public ResponseEntity<Task> updateTask(String token, RequestedUpdateTaskDTO requestedUpdateTaskDTO, String idUser) {
        var validatedUserId = validateTokenWithUserId(token, idUser);

        if (validatedUserId == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        if(validateTaskOwnerWithTaskId(requestedUpdateTaskDTO.getId(), validatedUserId) == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        var updatedTask = taskServiceApplication.update(requestedUpdateTaskDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedTask.getId())
                .toUri();

        return ResponseEntity.created(uri).body(updatedTask);
    }

    @Override
    public ResponseEntity deleteTask(String token, String idUser, String idTask) {

        var validatedUserId = validateTokenWithUserId(token, idUser);

        if (validatedUserId == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        if(validateTaskOwnerWithTaskId(idTask, validatedUserId) == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        taskServiceApplication.delete(idTask);
        return ResponseEntity.noContent().build();
    }

    private String validateTokenWithUserId(String token, String idUser) {
        System.out.println("[INICIO] Validate token");
        var userByToken = tokenService.getUserByToken(token);
        System.out.println("[FIM] Validate token");

        return userByToken != null && userByToken.getId().equals(idUser) ? userByToken.getId() : null;

    }

    private String validateTaskOwnerWithTaskId(String idTask, String validatedUserId) {
        var userOwnerByTask= taskServiceApplication.findTaskById(idTask)
                .getUser();
        return userOwnerByTask != null && userOwnerByTask.getId().equals(validatedUserId) ? userOwnerByTask.getId() : null;
    }
}
