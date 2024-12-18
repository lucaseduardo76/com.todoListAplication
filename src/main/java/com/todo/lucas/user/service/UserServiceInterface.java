package com.todo.lucas.user.service;

import com.todo.lucas.user.domain.RegisterDTO;
import com.todo.lucas.user.domain.ReplaceUserDTO;
import com.todo.lucas.user.domain.User;

public interface UserServiceInterface {

    User save(RegisterDTO registerDTO);
    User findByIdOrElseThrowException(String id);
    User replace(ReplaceUserDTO replaceUserDTO);
    void delete(String id);


}
