package com.example.demojparelationship.controller;

import com.example.demojparelationship.one_to_many.Author;
import com.example.demojparelationship.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class BlogController {
    @Autowired
    private final BlogService blogService;

    @GetMapping("/author")
    public Author getAuthor(){
        return blogService.getAuthor();
    }
}
