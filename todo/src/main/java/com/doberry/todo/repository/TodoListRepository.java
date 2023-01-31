package com.doberry.todo.repository;

import com.doberry.todo.dto.TodoListWithTasksDto;
import com.doberry.todo.entity.Task;
import com.doberry.todo.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Collection;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    @Query("SELECT tl FROM TodoList tl LEFT JOIN tl.tasks t")
    Collection<TodoList> findAllLeftJointTodoListAndTask();
}
