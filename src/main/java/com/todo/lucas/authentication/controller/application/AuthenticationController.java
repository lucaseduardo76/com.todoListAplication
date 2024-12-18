package com.todo.lucas.authentication.controller.application;

import com.todo.lucas.authentication.controller.inter.AuthenticationAPI;
import com.todo.lucas.user.domain.AuthenticationUserDTO;
import com.todo.lucas.user.domain.LoginResponseUserDTO;
import com.todo.lucas.user.domain.RegisterDTO;
import com.todo.lucas.authentication.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("'auth")
public class AuthenticationController  implements AuthenticationAPI {


    @Autowired
    private AuthenticationService authenticationService;


    @Override
    public ResponseEntity<LoginResponseUserDTO> login(@RequestBody @Valid AuthenticationUserDTO data){

        LoginResponseUserDTO login = authenticationService.loginAuthentication(data);
        return ResponseEntity.ok().body(login);
    }

    @Override
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO){
        var user = authenticationService.registerAuthentication(registerDTO);

        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(Collections.singletonMap("message", "Email already in use"));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(user);
    }



}
