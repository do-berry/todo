package com.doberry.todo.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TaskDto {

    @NotNull
    private Long id;

    @NotNull
    private String description;

    private boolean isDone;

    @NotNull
    private Long listId;
}
