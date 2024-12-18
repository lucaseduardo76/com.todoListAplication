package com.todo.lucas.task.domain;

import com.todo.lucas.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_task")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;
    private LocalDate initialDate;
    private LocalDate endDate;
    private StatusRole status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
