package hieu.example.todolist.service;

import hieu.example.todolist.exception.NotFoundException;
import hieu.example.todolist.entity.Todo;
import hieu.example.todolist.repository.TodoRepository;
import hieu.example.todolist.request.CreateTodoRequest;
import hieu.example.todolist.request.UpdateTodoRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class TodoService {
    @Autowired
    private final TodoRepository todoRepository;

    public List<Todo> getAllTodo(){
        List<Todo> todos = todoRepository.findAll();
        return todos;
    }

    public Todo getTodoById(Integer id){
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()){
            return todo.get();
        }
        throw new NotFoundException("Not found todo with id = " + id);
    }

    public Todo createTodo(CreateTodoRequest request){
        return todoRepository.save(new Todo(null, request.getTitle(), false));
    }
    public Todo updateTodo(Integer id, UpdateTodoRequest request){
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()){
            Todo todoData =  todo.get();
            todoData.setTitle(request.getTitle());
            todoData.setStatus(request.getStatus());
            return todoRepository.save(todoData);
        }
        throw new NotFoundException("Not found todo with id = " + id);
    }
    public void deleteTodo(Integer id){
        todoRepository.deleteById(id);
        System.out.println("xoa todo " + id);
    }
}

