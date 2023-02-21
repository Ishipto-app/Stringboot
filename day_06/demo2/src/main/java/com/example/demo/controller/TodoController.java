package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class TodoController {

    // Xử lý request "/users" có method GET
    @GetMapping("")
    public ResponseEntity<?> getListUser() {

    }
    //optional strim

    // Xử lý request "/users" có method POST
    @PostMapping("")
    public ResponseEntity<?> createUser() {

    }

    // Xử lý request "/users/{id}" có method PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser() {

    }

    // Xử lý request "/users/{id}" có method DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser() {

    }

}
