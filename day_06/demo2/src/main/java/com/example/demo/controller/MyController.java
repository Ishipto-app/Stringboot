package com.example.demo.controller;

import com.example.demo.modal.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @RequestMapping(value={"/hello-word","/hello"})
    public User helloWorld(){
        User user = new User(1,"ABC");
        return user;
    }
}
