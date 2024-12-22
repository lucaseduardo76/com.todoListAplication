package com.todo.lucas.user.controller.inter;

import com.todo.lucas.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public interface UserControllerInteface {

    @GetMapping
    ResponseEntity<User> findUserByToken(@RequestHeader(name = "Authorization", required = true) String token);

}
