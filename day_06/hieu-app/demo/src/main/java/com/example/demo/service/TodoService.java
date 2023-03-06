package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Todo;
import com.example.demo.request.CreateTodoRequest;
import com.example.demo.request.UpdateTodoRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

// chu y danh dau service ban chat la 1 component / khi chuong trinh khoi tao service nay se duoc dua vao container quan ly
// khi muon su dung service o trong controller thi vao container lay ra (Inject Bean) khi do se su dung dc cac phuong thuc cua service
@Service
public class TodoService {
    // service muon lay du lieu phai qua Data access layer lay du lieu tu data
    // vi chua co database nen tam thoi bo qua buoc nay
    // du lieu ly tuong khi them can them vao contructor
    private List<Todo> todos;
    public TodoService(){
        todos = new ArrayList<>();
        todos.add(new Todo(1, "lam bai tap", true));//, LocalDateTime.now(), "normal"));
        todos.add(new Todo(2, "Da bong", true));//, LocalDateTime.now(), "medium"));
        todos.add(new Todo(3, "Di choi", false));//, LocalDateTime.now(), "high"));
    }
    private int generateId(){
        //max = 1000 min = 100
        //return (int) Math.floor(Math.random() + (1000 - 100 + 1) + 100 );
        Random rd = new Random();
        return rd.nextInt(1000 - 100 + 1) + 100;
    }
    public List<Todo> getAllTodo(){
        return todos;
    }
    // doi tuong optional tu tim hieu
    // doi truong strim api tu tim hieu

    // error => object nguyen nhan loi (message, path, time...)
    // status: ma loi
    public Todo getTodoById(Integer id){
        for (Todo t: todos) {
            if(Objects.equals(t.getId(), id)) {
                return t;
            }
        }
        throw new NotFoundException("Not found todo with id = " + id);
    }
//
    public Todo createTodo(CreateTodoRequest request){
        if(request.getTitle().isEmpty()){
            //throw new Exception();
        }
        // neu dung builder co the thay doi thu tu cac thuoc tinh
        Todo todo = Todo.builder()
                .id(generateId())
                .status(false)
                .title(request.getTitle())
//                .createAt(LocalDateTime.now())
//                .level(request.getLevel())
                .build();
        todos.add(todo);
        return todo;
    }
    public Todo updateTodo(Integer id, UpdateTodoRequest request){
        for (Todo t: todos) {
            if(Objects.equals(t.getId(), id)) {
                t.setTitle(request.getTitle());
                t.setStatus(request.getStatus());
//                t.setLevel(request.getLevel());
                return t;
            }
        }
        return null;
    }
    public void deleteTodo(Integer id){
        // bieu thuc landa su dung de trien khai 1 function interface (la interface ben trong co duy nhat 1 methol co the tu custom)
        todos.removeIf(todo -> Objects.equals(todo.getId(), id));
        System.out.println("xoa todo " + id);
    }
}
