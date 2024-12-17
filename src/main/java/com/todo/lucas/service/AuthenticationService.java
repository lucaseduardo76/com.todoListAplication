package com.todo.lucas.service;

import com.todo.lucas.domain.user.AuthenticationDTO;
import com.todo.lucas.domain.user.LoginResponseDTO;
import com.todo.lucas.domain.user.RegisterDTO;
import com.todo.lucas.domain.user.User;
import com.todo.lucas.infra.security.TokenService;
import com.todo.lucas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AuthenticationService {



    private final TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    public AuthenticationService() {
        tokenService = new TokenService();
    }

    public LoginResponseDTO loginAuthentication(AuthenticationDTO data){

        System.out.println("Tentativa de login para: " + data.getEmail());
        var emailAndPassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());

        try{
            var auth = authenticationManager.authenticate(emailAndPassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return new LoginResponseDTO(token);
        }catch (Exception exception){
            System.out.println("Entrou aqui");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
    }

    public User registerAuthentication(RegisterDTO registerDTO){
        if(this.userRepository.findByEmail(registerDTO.getEmail()).isPresent()) return null;

        String encryptedPassoword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        User user = new User(registerDTO.getEmail(), encryptedPassoword,
                registerDTO.getName(), registerDTO.getPhone(),registerDTO.getCpf());
        return userRepository.save(user);
    }


}
