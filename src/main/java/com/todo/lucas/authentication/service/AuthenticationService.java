package com.todo.lucas.authentication.service;

import com.todo.lucas.user.domain.AuthenticationUserDTO;
import com.todo.lucas.user.domain.LoginResponseUserDTO;
import com.todo.lucas.user.domain.RegisterDTO;
import com.todo.lucas.user.domain.User;
import com.todo.lucas.exception.BadRequestException;
import com.todo.lucas.infra.security.TokenService;
import com.todo.lucas.user.repository.UserRepository;
import com.todo.lucas.user.service.UserServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {



    private final TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceApplication userService;

    public AuthenticationService() {
        tokenService = new TokenService();
    }

    public LoginResponseUserDTO loginAuthentication(AuthenticationUserDTO data){


        var emailAndPassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());

        try{
            var auth = authenticationManager.authenticate(emailAndPassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return new LoginResponseUserDTO(token);
        }catch (Exception exception){
            throw new BadRequestException( "Invalid email or password");
        }
    }

    public User registerAuthentication(RegisterDTO registerDTO){
        if(this.userRepository.findByEmail(registerDTO.getEmail()).isPresent()) return null;

        return userService.save(registerDTO);
    }


}
