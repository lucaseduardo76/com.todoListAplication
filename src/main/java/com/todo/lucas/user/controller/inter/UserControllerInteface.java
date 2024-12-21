package com.todo.lucas.user.controller.inter;

import com.todo.lucas.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public interface UserControllerInteface {

    ResponseEntity<User>

}
