package com.doberry.todo.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoListDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private LocalDate creationDate;
}
