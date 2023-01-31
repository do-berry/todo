package com.doberry.todo.mapper;

import com.doberry.todo.dto.TodoListDto;
import com.doberry.todo.entity.TodoList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Map from {@link com.doberry.todo.dto.TodoListDto} to {@link com.doberry.todo.entity.TodoList}
 */
public class TodoListMapper {

    @NotNull
    public TodoList mapFrom(@Valid @NotNull TodoListDto dto) {
        final TodoList todoList = new TodoList();
        todoList.setName(dto.getName());
        todoList.setCreationDate(dto.getCreationDate());
        todoList.setId(dto.getId());
        return todoList;
    }

    public TodoListMapper() {
    }
}
