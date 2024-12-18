package com.todo.lucas.user.service;


import com.todo.lucas.user.domain.RegisterDTO;
import com.todo.lucas.user.domain.ReplaceUserDTO;
import com.todo.lucas.user.domain.User;
import com.todo.lucas.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceApplication implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(RegisterDTO registerDTO) {
        return userRepository.save(buildUser(registerDTO));
    }

    @Override
    public User findByIdOrElseThrowException(String id) {
        return userRepository.findById(id).orElseThrow( () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @Override
    public User replace(ReplaceUserDTO replaceUserDTO){
        User previousUser = findByIdOrElseThrowException(replaceUserDTO.id());
        return userRepository.save(buildReplacedUser(replaceUserDTO, previousUser));
    }

    @Override
    public void delete(String id) {
        userRepository.delete(findByIdOrElseThrowException(id));
    }

    private String encryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    private User buildUser(RegisterDTO registerDTO) {
        return new User(registerDTO.getEmail(), encryptPassword(registerDTO.getPassword()),
                registerDTO.getName(), registerDTO.getPhone(),registerDTO.getCpf());
    }

    private User buildReplacedUser(ReplaceUserDTO replaceUserDTO, User previousUser) {
        return new User(replaceUserDTO.id(), replaceUserDTO.email(),previousUser.getPassword(),
                replaceUserDTO.name(), replaceUserDTO.phone(), previousUser.getCpf());
    }




}
