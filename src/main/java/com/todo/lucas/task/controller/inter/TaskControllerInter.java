package com.todo.lucas.task.controller.inter;


import com.todo.lucas.infra.security.TokenService;
import com.todo.lucas.task.domain.RequestedNewTaskDTO;
import com.todo.lucas.task.domain.RequestedUpdateTaskDTO;
import com.todo.lucas.task.domain.Task;
import com.todo.lucas.task.service.TaskServiceApplication;
import com.todo.lucas.user.service.UserServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public interface TaskControllerInter {

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Task>> getAllTasks(@RequestHeader(name = "Authorization", required = true) String token,
                                                  @RequestParam String idUser);

    @GetMapping("/getTaskById")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@RequestHeader(name = "Authorization", required = true) String token,
                                                  @RequestParam String idUser, @RequestParam String idTask);

    @PostMapping("newTask")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Task> createTask(@RequestHeader(name = "Authorization", required = true) String token,
                                           @RequestBody RequestedNewTaskDTO requestedNewTaskDTO,
                                           @RequestParam String idUser);

    @PutMapping("/editTask")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Task> updateTask(@RequestHeader(name = "Authorization", required = true) String token,
                                           @RequestBody RequestedUpdateTaskDTO requestedUpdateTaskDTO,
                                           @RequestParam String idUser);

    @DeleteMapping("/deleteTask")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity deleteTask(@RequestHeader(name = "Authorization", required = true) String token,
                           @RequestParam String idUser, @RequestParam String idTask);
}
