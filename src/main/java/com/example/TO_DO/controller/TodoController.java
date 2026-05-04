package com.example.TO_DO.controller;

import com.example.TO_DO.model.Todo;
import com.example.TO_DO.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    //get a todos using id with http GET
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    //create new todos using http POST
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(todo));
    }

    //update a todos using http PUT
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.updateTodo(id, todo));
    }

    //delete a todos using http DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }


}
