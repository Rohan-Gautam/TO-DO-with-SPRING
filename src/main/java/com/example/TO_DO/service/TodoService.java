package com.example.TO_DO.service;

import com.example.TO_DO.model.Todo;
import com.example.TO_DO.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService (TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //get all todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    //get a todo_by id
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    }

    //create new todos
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    //update todos
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo existing = getTodoById(id);
        existing.setTitle(updatedTodo.getTitle());
        existing.setDescription(updatedTodo.getDescription());
        existing.setCompleted(updatedTodo.isCompleted());
        return todoRepository.save(existing);
    }

    //delete todos
    public void deleteTodo(Long id) {
        Todo existing = getTodoById(id);
        todoRepository.deleteById(existing.getId());
    }
}
