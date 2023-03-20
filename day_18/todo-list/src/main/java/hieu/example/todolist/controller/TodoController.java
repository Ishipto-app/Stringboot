package hieu.example.todolist.controller;

import hieu.example.todolist.exception.ErrorResponse;
import hieu.example.todolist.exception.NotFoundException;
import hieu.example.todolist.entity.Todo;
import hieu.example.todolist.request.CreateTodoRequest;
import hieu.example.todolist.request.UpdateTodoRequest;
import hieu.example.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class TodoController {

    private final TodoService todoService;
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping("todos")
    public List<Todo> getAllTodo(){
        return todoService.getAllTodo();
    }
    @GetMapping("todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Integer id){
        Todo todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping("todos")
    public ResponseEntity<?> createTodo(@Valid @RequestBody CreateTodoRequest request) {
        System.out.println(request.getTitle());
        Todo todo = todoService.createTodo(request);
        return new ResponseEntity(todo, HttpStatus.CREATED);
    }

    @PutMapping("todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Integer id,@Valid @RequestBody UpdateTodoRequest request){
        Todo todo = todoService.updateTodo(id, request);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteTodo(@PathVariable Integer id){
        todoService.deleteTodo(id);
    }

}

