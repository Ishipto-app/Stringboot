package com.example.demojparelationship;

import com.example.demojparelationship.one_to_many.Author;
import com.example.demojparelationship.one_to_many.AuthorRepository;
import com.example.demojparelationship.one_to_many.Blog;
import com.example.demojparelationship.one_to_many.BlogRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class OneToManyTests {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BlogRepository blogRepository;

    @Test
    void save_author_blog() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            // Tạo blog
            List<Blog> blogs = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Blog blog = Blog.builder()
                        .title(faker.book().title())
                        .build();
                blogs.add(blog);
            }

            // Tạo author
            Author author = Author.builder()
                    .name(faker.name().fullName())
                    .blogs(blogs)
                    .build();
            authorRepository.save(author);
        }
    }

    @Test
    void save_author_blog_2() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            // Tạo author
            Author author = Author.builder()
                    .name(faker.name().fullName())
                    .build();
            authorRepository.save(author);

            // Tạo blog
            for (int j = 0; j < 3; j++) {
                Blog blog = Blog.builder()
                        .title(faker.book().title())
                        .author(author)
                        .build();

                blogRepository.save(blog);
            }
        }
    }

    @Test
    void get_author() {

        Optional<Author> author = authorRepository.findById(6);
        Author data = author.get();
        System.out.println(data);

//        Author author = authorRepository.findById(6).orElse(null);
//        System.out.println(author);

//        List<Blog> list = author.getBlogs();
//        list.remove(list.get(0));
    }
    @Test
    void get_blog() {
        Blog blog = blogRepository.findById(16).orElse(null);
        System.out.println(blog);

//        List<Blog> list = author.getBlogs();
//        list.remove(list.get(0));
    }

    // Câu hỏi : Nếu xóa tác giả -> bài viết tương ứng không xóa khỏi db, mà author_id = null thì làm như thế nào?
    // Sử dụng @PreRemove
}
