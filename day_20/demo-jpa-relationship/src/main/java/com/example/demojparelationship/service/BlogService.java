package com.example.demojparelationship.service;

import com.example.demojparelationship.one_to_many.Author;
import com.example.demojparelationship.one_to_many.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {
    private final AuthorRepository authorRepository;

    public BlogService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor() {
        Optional<Author> author = authorRepository.findById(6);
        Author data = author.get();
        return data;
    }
}
