package com.doberry.todo.entity;

import javax.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String description;

    private boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoList.id")
    private TodoList todoList;
}
