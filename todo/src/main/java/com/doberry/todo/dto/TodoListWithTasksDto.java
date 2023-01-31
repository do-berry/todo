package com.doberry.todo.dto;

import com.doberry.todo.entity.TodoList;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class TodoListWithTasksDto extends TodoListDto {

    private Collection<TaskDto> tasks = new ArrayList<>();
}
