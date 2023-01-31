package com.doberry.todo.controller;

import com.doberry.todo.dto.TodoListDto;
import com.doberry.todo.dto.TodoListWithTasksDto;
import com.doberry.todo.dto.UpdateTodoListDto;
import com.doberry.todo.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/todo-list")
public class TodoListController {

    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/create")
    public ResponseEntity<TodoListDto> createTodoList(@RequestBody @Valid TodoListDto todoListDto) {
        final TodoListDto createdTodoList = todoListService.createList(todoListDto);
        return ResponseEntity.created(URI.create("/" + createdTodoList.getId())).body(createdTodoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TodoListDto> updateTodoList(@PathVariable @NotNull Long id,
                                                      @RequestBody @NotNull final UpdateTodoListDto updateTodoListDto) {
        return ResponseEntity.ok(todoListService.updateList(id, updateTodoListDto.getName()));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodoList(@PathVariable @NotNull Long id) {
        todoListService.deleteList(id);
    }

    @GetMapping
    public ResponseEntity<Collection<TodoListWithTasksDto>> findAllTodoListsWithTasks() {
        return ResponseEntity.ok(todoListService.findAllTodoListsWithTasks());
    }
}
