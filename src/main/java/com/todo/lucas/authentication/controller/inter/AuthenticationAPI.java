package com.todo.lucas.authentication.controller.inter;

import com.todo.lucas.user.domain.AuthenticationUserDTO;
import com.todo.lucas.user.domain.LoginResponseUserDTO;
import com.todo.lucas.user.domain.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public interface AuthenticationAPI {


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<LoginResponseUserDTO> login(@RequestBody @Valid AuthenticationUserDTO data);


    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO);

}
