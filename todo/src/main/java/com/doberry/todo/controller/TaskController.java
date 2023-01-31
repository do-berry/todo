package com.doberry.todo.controller;

import com.doberry.todo.dto.TaskDto;
import com.doberry.todo.dto.UpdateTaskDto;
import com.doberry.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody @Valid TaskDto taskDto) {
        final TaskDto createdTask = taskService.createTask(taskDto);
        return ResponseEntity.created(URI.create("/" + createdTask.getId())).body(createdTask);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable @NotNull Long id,
                                              @RequestBody @NotNull final UpdateTaskDto updateTaskDto) {
        return ResponseEntity.ok(taskService.updateTask(id, updateTaskDto.getDescription()));
    }

    @PutMapping("/done/{id}")
    public void changeDoneStatus(@PathVariable @NotNull Long id) {
        taskService.changeDoneStatus(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable @NotNull Long id) {
        taskService.deleteTask(id);
    }
}
