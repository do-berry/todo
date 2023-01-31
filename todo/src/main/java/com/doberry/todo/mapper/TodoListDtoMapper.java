package com.doberry.todo.mapper;

import com.doberry.todo.dto.TodoListDto;
import com.doberry.todo.entity.TodoList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Map from {@link com.doberry.todo.entity.TodoList} to {@link com.doberry.todo.dto.TodoListDto}
 */
public class TodoListDtoMapper {

    @NotNull
    public TodoListDto mapFrom(@Valid @NotNull final TodoList todoList) {

        final TodoListDto dto = new TodoListDto();
        dto.setId(todoList.getId());
        dto.setCreationDate(todoList.getCreationDate());
        dto.setName(todoList.getName());
        return dto;
    }
}
