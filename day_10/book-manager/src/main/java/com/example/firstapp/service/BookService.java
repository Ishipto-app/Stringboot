package com.example.firstapp.service;

import com.example.firstapp.exception.NotFoundException;
import com.example.firstapp.model.Book;
import com.example.firstapp.repository.BookRepository;
import com.example.firstapp.request.UpsertBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
//        Optional<Book> bookOptional = bookRepository.findById(id);
//        if(bookOptional.isPresent()) {
//            return bookOptional.get();
//        }
//        throw new NotFoundException("Not found book with id = " + id);

        return bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });
    }

    public Book createBook(UpsertBookRequest request) {
        Random rd = new Random();
        int id = rd.nextInt(1000);

        Book book = new Book(
                id,
                request.getTitle(),
                request.getDescription(),
                request.getPublishYear());

        bookRepository.save(book);
        return book;
    }

    // Exception
    // Lambda expression
    // Stream
    // Functional Interface
    // Method Reference
    // Generic
    public Book updateBook(int id, UpsertBookRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });

        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setPublishYear(request.getPublishYear());

        return book;
    }

    public void deleteBook(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });

        bookRepository.delete(book);
    }
}
