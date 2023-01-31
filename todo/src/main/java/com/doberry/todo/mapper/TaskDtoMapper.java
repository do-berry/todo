package com.doberry.todo.mapper;

import com.doberry.todo.dto.TaskDto;
import com.doberry.todo.entity.Task;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Map from {@link com.doberry.todo.entity.Task} to {@link com.doberry.todo.dto.TaskDto}
 */
public class TaskDtoMapper {

    @NotNull
    public TaskDto mapFrom(@NotNull Task task) {
        final TaskDto taskDto = new TaskDto();
        taskDto.setDescription(task.getDescription());
        taskDto.setDone(task.isDone());
        taskDto.setId(task.getId());
        taskDto.setListId(task.getTodoList().getId());
        return taskDto;
    }

    @NotNull
    public Collection<TaskDto> mapFrom(@NotNull Collection<Task> tasks) {
        return tasks.stream()
                .map(this::mapFrom)
                .collect(Collectors.toList());
    }
}
