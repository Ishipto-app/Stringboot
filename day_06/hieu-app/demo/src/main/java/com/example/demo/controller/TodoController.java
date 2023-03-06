package com.example.demo.controller;

import com.example.demo.exception.ErrorResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Todo;
import com.example.demo.request.CreateTodoRequest;
import com.example.demo.request.UpdateTodoRequest;
import com.example.demo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// CRUD
@RestController
@RequestMapping("api")
// dinh nghia tat ca cac duong dan bat dau = api
public class TodoController {
    //Inject Bean
    //Field
    //Constructor
    // inject TodoService
    private final TodoService todoService;
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }
    //Setter


    @GetMapping("todos")
    public List<Todo> getAllTodo(){
        return todoService.getAllTodo();
    }
    @GetMapping("todos/{id}")
    //ngoai thong tin ve to do tra ve thong tin khac dung ResponseEntity
    public ResponseEntity<?> getTodoById(@PathVariable Integer id){
//        try {
//            //return todoService.getTodoById(id);
//            Todo todo = todoService.getTodoById(id);
//            return ResponseEntity.ok(todo);
//        } catch (NotFoundException e){
//            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
//            return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
//        }

        // sau khi co CustomerExceptionHandler thi khi co loi no tu nhan loi trong do
        System.out.println("update " + id);
        Todo todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    // tao moi du lieu ktra xem du lieu trong Todo cai nao bat buoc phai gui len
    //thay doi responstatus
    @PostMapping("todos")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Todo createTodo(@Valid @RequestBody CreateTodoRequest request) {
        return todoService.createTodo(request);
    }
    // thay doi du lieu
    @PutMapping("todos/{id}")
    public Todo updateTodo(@PathVariable Integer id,@Valid @RequestBody UpdateTodoRequest request){
        System.out.println("update " + request.getStatus() + " id = " + id);
        return todoService.updateTodo(id, request);
    }

    //xoa du lieu

    @DeleteMapping("todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteTodo(@PathVariable Integer id){
        todoService.deleteTodo(id);
    }

    //tao ra service xu ly du lieu request
}
