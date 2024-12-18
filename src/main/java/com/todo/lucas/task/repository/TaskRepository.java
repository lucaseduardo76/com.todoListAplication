package com.todo.lucas.task.repository;

import com.todo.lucas.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, String> {
    Optional<Task> findByTitle(String title);
    Optional<Task> findByInitialDate(String date);
    Optional<Task> findByEndDate(String date);
}
