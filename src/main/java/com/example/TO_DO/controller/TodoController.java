package com.example.TO_DO.controller;

import com.example.TO_DO.dto.TodoRequest;
import com.example.TO_DO.dto.TodoResponse;
import com.example.TO_DO.model.User;
import com.example.TO_DO.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    //get all todos using http GET
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(todoService.getAllTodos(user));
    }

    //get a todos using id with http GET
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(todoService.getTodoById(id, user));
    }

    //create new todos using http POST
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest request, @AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(request, user));
    }

    //update a todos using http PUT
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id,@Valid @RequestBody TodoRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(todoService.updateTodo(id, request, user));
    }

    //delete a todos using http DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id, @AuthenticationPrincipal User user) {
        todoService.deleteTodo(id, user);
        return ResponseEntity.noContent().build();
    }


}
