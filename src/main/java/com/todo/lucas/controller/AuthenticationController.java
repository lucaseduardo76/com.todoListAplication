package com.todo.lucas.controller;

import com.todo.lucas.domain.user.AuthenticationDTO;
import com.todo.lucas.domain.user.LoginResponseDTO;
import com.todo.lucas.domain.user.RegisterDTO;
import com.todo.lucas.domain.user.User;
import com.todo.lucas.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        LoginResponseDTO login = authenticationService.loginAuthentication(data);
        return ResponseEntity.ok().body(login);
    }

    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO){


        var user = authenticationService.registerAuthentication(registerDTO);

        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Email already in use"));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(user);
    }



}
