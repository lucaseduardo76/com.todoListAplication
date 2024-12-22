package com.todo.lucas.user.controller.application;

import com.todo.lucas.infra.security.TokenService;
import com.todo.lucas.user.controller.inter.UserControllerInteface;
import com.todo.lucas.user.domain.User;
import com.todo.lucas.user.service.UserServiceApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController implements UserControllerInteface {

    private final TokenService tokenService;
    private final UserServiceApplication userServiceApplication;

    @Override
    public ResponseEntity<User> findUserByToken(String token) {
        User user = tokenService.getUserByToken(token);
        return ResponseEntity.ok(user);
    }
}
