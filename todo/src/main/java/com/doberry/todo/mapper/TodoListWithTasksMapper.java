package com.doberry.todo.mapper;

import com.doberry.todo.dto.TaskDto;
import com.doberry.todo.dto.TodoListWithTasksDto;
import com.doberry.todo.entity.TodoList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

public class TodoListWithTasksMapper {

    private TaskDtoMapper taskDtoMapper;

    @Autowired
    public TodoListWithTasksMapper() {
        this.taskDtoMapper = new TaskDtoMapper();
    }

    @NotNull
    private TodoListWithTasksDto mapFrom(@NotNull final TodoList todoList) {
        final TodoListWithTasksDto todoListWithTasksDto = new TodoListWithTasksDto();
        todoListWithTasksDto.setTasks(taskDtoMapper.mapFrom(todoList.getTasks()));
        todoListWithTasksDto.setCreationDate(todoList.getCreationDate());
        todoListWithTasksDto.setId(todoList.getId());
        todoListWithTasksDto.setName(todoList.getName());
        return todoListWithTasksDto;
    }

    @NotNull
    public Collection<TodoListWithTasksDto> mapFrom(@NotNull Collection<TodoList> todoLists) {
        return todoLists.stream()
                .map(this::mapFrom)
                .collect(Collectors.toList());
    }
}
