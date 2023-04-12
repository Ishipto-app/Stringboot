package com.example.thymeleafspingsecurity;

import com.example.thymeleafspingsecurity.entity.User;
import com.example.thymeleafspingsecurity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ThymeleafSpingsecurityApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser() {
        User user = new User(1, "user", "abc1", passwordEncoder.encode("123"), List.of("USER"));
        User admin = new User(2, "admin", "abc2", passwordEncoder.encode("123"), List.of("ADMIN", "USER"));
        User author = new User(3, "author", "abc3", passwordEncoder.encode("123"), List.of("AUTHOR"));

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(author);
    }
    @Test
    void save_user2() {
        User user = new User(null, "Hien", "hien@gmail.com", passwordEncoder.encode("111"), List.of("USER"));

        User admin = new User(null, "An", "an@gmail.com", passwordEncoder.encode("111"), List.of("USER", "ADMIN"));

        User author = new User(null, "Tuan", "tuan@gmail.com", passwordEncoder.encode("111"), List.of("AUTHOR"));

        userRepository.save(admin);
        userRepository.save(user);
        userRepository.save(author);
    }
}
