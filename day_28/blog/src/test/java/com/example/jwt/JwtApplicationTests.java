package com.example.jwt;

import com.example.jwt.entity.*;
import com.example.jwt.repository.*;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class JwtApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void save_roles() {
        List<Role> roles = List.of(
                new Role(null, "ADMIN"),
                new Role(null, "USER"),
                new Role(null, "AUTHOR")
        );

        roleRepository.saveAll(roles);
    }

    @Test
    void save_users() {
        Role userRole = roleRepository.findByName("USER").orElse(null);
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
        Role authorRole = roleRepository.findByName("AUTHOR").orElse(null);

        List<User> users = List.of(
                new User(null, "Bùi Hiên", "hien@gmail.com", passwordEncoder.encode("111"), null, List.of(adminRole, userRole)),
                new User(null, "Minh Duy", "duy@gmail.com", passwordEncoder.encode("111"), null, List.of(userRole)),
                new User(null, "Thu Hằng", "hang@gmail.com", passwordEncoder.encode("111"), null, List.of(authorRole, userRole))
        );

        userRepository.saveAll(users);
    }

    @Test
    void save_categories(){
        List<Category> categories = List.of(
                new Category(null, "Backend"),
                new Category(null, "Fontend"),
                new Category(null, "Devops"),
                new Category(null, "Database"),
                new Category(null, "Mobile")
        );

        categoryRepository.saveAll(categories);
    }

    @Test
    void save_blogs(){
        Slugify slugify = Slugify.builder().build();
        Random rd = new Random();
        List<User> users = userRepository.findByRoles_NameIn(List.of(
                "ADMIN",
                "AUTHOR"
        ));
        List<Category> categories = categoryRepository.findAll();
        for (int i = 0; i < 25; i++) {
            //random 1 user
            User rdUser = users.get(rd.nextInt(users.size()));

            //radmon 1 list category
            List<Category> rdCategoryList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Category rdCategory = categories.get(rd.nextInt(categories.size()));
                if(!rdCategoryList.contains(rdCategory)){
                    rdCategoryList.add(rdCategory);
                }
            }

            //tao blog
            Blog blog = Blog.builder()
                    .title("Blog " + (i + 1))
                    .slug(slugify.slugify("Blog " + (i + 1)))
                    .description("Description" + (i + 1))
                    .content("Content" + (i + 1))
                    .status(rd.nextInt(2) == 0)
                    .user(rdUser)
                    .categories(rdCategoryList)
                    .build();
            blogRepository.save(blog);

        }
    }

    @Test
    void save_comments(){
        Random rd = new Random();
        List<User> users = userRepository.findAll();
        List<Blog> blogs = blogRepository.findAll();

        for (int i = 0; i < 100; i++) {
            //random 1 user
            User rdUser = users.get(rd.nextInt(users.size()));
            //random 1 blog
            Blog rdBlog = blogs.get(rd.nextInt(blogs.size()));
            //tao comment
            Comment comment = Comment.builder()
                    .content("Content" + (i + 1))
                    .user(rdUser)
                    .blog(rdBlog)
                    .build();
            commentRepository.save(comment);
        }

    }
}
