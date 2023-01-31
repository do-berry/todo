package com.doberry.todo.mapper;

import com.doberry.todo.dto.TaskDto;
import com.doberry.todo.entity.Task;
import com.doberry.todo.entity.TodoList;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Map from {@link com.doberry.todo.dto.TaskDto} to {@link com.doberry.todo.entity.Task}
 */
public class TaskMapper {

    @NotNull
    public Task mapFrom(@NotNull TaskDto taskDto,
                        @NotNull TodoList todoList)  {
        final Task task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setDone(taskDto.isDone());
        task.setId(taskDto.getId());
        task.setTodoList(todoList);
        return task;
    }
}
