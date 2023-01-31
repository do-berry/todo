package com.doberry.todo.service;

import com.doberry.todo.dto.TodoListDto;
import com.doberry.todo.dto.TodoListWithTasksDto;
import com.doberry.todo.dto.UpdateTodoListDto;
import com.doberry.todo.entity.TodoList;
import com.doberry.todo.mapper.TodoListDtoMapper;
import com.doberry.todo.mapper.TodoListMapper;
import com.doberry.todo.mapper.TodoListWithTasksMapper;
import com.doberry.todo.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.SystemException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {

    private final TodoListMapper todoListMapper;

    private final TodoListDtoMapper todoListDtoMapper;

    private final TodoListRepository todoListRepository;

    private TodoListWithTasksMapper todoListWithTasksMapper;

    @Autowired
    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListMapper = new TodoListMapper();
        this.todoListRepository = todoListRepository;
        this.todoListDtoMapper = new TodoListDtoMapper();
        this.todoListWithTasksMapper = new TodoListWithTasksMapper();
    }

    @NotNull
    public TodoListDto createList(@Valid @NotNull TodoListDto todoListDto) {
        final TodoList todoList = todoListMapper.mapFrom(todoListDto);
        todoListRepository.save(todoList);

        return todoListDtoMapper.mapFrom(todoList);
    }

    @NotNull
    public TodoListDto updateList(@NotNull Long id, @NotNull String updatedName) {
        final TodoList todoList = todoListRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("TodoList with id: " + id + " not found."));
        todoList.setName(updatedName);
        todoListRepository.save(todoList);
        return todoListDtoMapper.mapFrom(todoList);
    }

    public void deleteList(@NotNull Long id) {
        todoListRepository.deleteById(id);
    }

    @NotNull
    public Collection<TodoListWithTasksDto> findAllTodoListsWithTasks() {
        return todoListWithTasksMapper.mapFrom(todoListRepository.findAllLeftJointTodoListAndTask());
    }
}