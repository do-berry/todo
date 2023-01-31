package com.doberry.todo.service;

import com.doberry.todo.dto.TaskDto;
import com.doberry.todo.entity.Task;
import com.doberry.todo.entity.TodoList;
import com.doberry.todo.mapper.TaskDtoMapper;
import com.doberry.todo.mapper.TaskMapper;
import com.doberry.todo.repository.TaskRepository;
import com.doberry.todo.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class TaskService {

    private final TaskMapper taskMapper;

    private final TaskDtoMapper taskDtoMapper;

    private final TaskRepository taskRepository;

    private final TodoListRepository todoListRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository,
                       TodoListRepository todoListRepository) {
        this.taskMapper = new TaskMapper();
        this.taskDtoMapper = new TaskDtoMapper();
        this.taskRepository = taskRepository;
        this.todoListRepository = todoListRepository;
    }

    @NotNull
    public TaskDto createTask(@NotNull TaskDto taskDto) {
        final TodoList list = todoListRepository.findById(taskDto.getListId()).orElseThrow(() ->
                new IllegalStateException("No list with id " + taskDto.getId() + "found."));
        final Task task = taskMapper.mapFrom(taskDto, list);
        taskRepository.save(task);

        return taskDtoMapper.mapFrom(task);
    }

    public void deleteTask(@NotNull Long id) {
        taskRepository.deleteById(id);
    }

    @NotNull
    public TaskDto updateTask(@NotNull Long id, @NotNull String description) {
        final Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Task with id: " + id + " not found."));
        task.setDescription(description);
        taskRepository.save(task);
        return taskDtoMapper.mapFrom(task);
    }

    public void changeDoneStatus(@NotNull Long id) {
        final Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Task with id: " + id + " not found."));
        task.setDone(!task.isDone());
        taskRepository.save(task);
    }
}
