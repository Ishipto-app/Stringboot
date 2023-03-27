package com.example.demojpa;

import com.example.demojpa.one_to_many.Author;
import com.example.demojpa.one_to_many.Blog;
import com.example.demojpa.responsitory.AuthorRepository;
import com.example.demojpa.responsitory.BlogRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OneToManyTests {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BlogRepository blogRepository;

//    @Test
//    void save_author_blog(){
//        Faker faker = new Faker();
//        for (int i = 0; i < 5; i++) {
//            List<Blog> blogs = new ArrayList<>();
//            for (int j = 0; j < 3; j++) {
//                Blog blog = Blog.builder()
//                        .title(faker.book().title())
//                        .build();
//                blogs.add(blog);
//
//            }
//
//            Author author = Author.builder()
//                    .name(faker.name().fullName())
//                    .blogs(blogs)
//                    .build();
//            authorRepository.save(author);
//        }
//    }

//    @Test
//    @Transactional
//    void save_author_blog_2(){
//        Faker faker = new Faker();
//        for (int i = 0; i < 5; i++) {
//            Author author = Author.builder()
//                    .name(faker.name().fullName())
//                    .build();
//            authorRepository.save(author);
//            for (int j = 0; j < 3; j++) {
//                Blog blog = Blog.builder()
//                        .title(faker.book().title())
//                        .author(author)
//                        .build();
//                blogRepository.save(blog);
//            }
//
//        }
//    }
//
//    @Test
//    void get_author(){
//        Author author = authorRepository.findById(1).orElse(null);
//        List<Blog> list = author.getBlogs();
//        list.remove(list.get(0));
//    }

    // cau hoi neu xoa author -> bai viet ko xoa khoi db ma author_id = null thi lam the nao
    // trong life cycle su dung @PreRemove
}
