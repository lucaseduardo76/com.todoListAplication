package com.todo.lucas.user.repository;

import com.todo.lucas.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
        Optional<User> findByEmail(String email);
        Optional<User> findById(String id);
}
