package com.doberry.todo.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTodoListDto {

    @NotNull
    private String name;
}
