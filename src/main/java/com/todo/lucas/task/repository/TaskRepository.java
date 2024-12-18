package com.todo.lucas.task.repository;

import com.todo.lucas.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, String> {
    Optional<Task> findByTitle(String title);
    Optional<List<Task>> findByInitialDate(String date);
    Optional<List<Task>> findByEndDate(String date);
    Optional<List<Task>> findByUserId(String userId);

}
