package com.example.TO_DO.service;

import com.example.TO_DO.dto.TodoRequest;
import com.example.TO_DO.dto.TodoResponse;
import com.example.TO_DO.model.Todo;
import com.example.TO_DO.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService (TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //get all todos
    public List<TodoResponse> getAllTodos() {

        return todoRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    //get a todo_by id
    public TodoResponse getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                                      .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        return convertToResponse(todo);
    }

    //create new todos
    public TodoResponse createTodo(TodoRequest request) {
        Todo todo = convertToEntity(request);
        Todo saved = todoRepository.save(todo);
        return convertToResponse(saved);
    }

    //update todos
    public TodoResponse updateTodo(Long id, TodoRequest request) {
        Todo existing = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setCompleted(request.isCompleted());
        Todo saved = todoRepository.save(existing);
        return convertToResponse(saved);
    }

    //delete todos
    public void deleteTodo(Long id) {
        Todo existing = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        todoRepository.deleteById(existing.getId());
    }

    private Todo convertToEntity(TodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        return todo;
    }

    private TodoResponse convertToResponse(Todo todo) {
        return new TodoResponse(todo.getId(),
                                todo.getTitle(),
                                todo.getDescription(),
                                todo.isCompleted(),
                                todo.getCreatedAt());
    }
}
