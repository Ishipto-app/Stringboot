package com.example.registeruser;

import com.example.registeruser.entity.Role;
import com.example.registeruser.entity.TokenConfirm;
import com.example.registeruser.entity.User;
import com.example.registeruser.repository.RoleRepository;
import com.example.registeruser.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class RegisterUserApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void addRoles() {
        Role admin = new Role();
        admin.setName("ADMIN");
        roleRepository.save(admin);
        Role user = new Role();
        user.setName("USER");
        roleRepository.save(user);
        Role author = new Role();
        author.setName("AUTHOR");
        roleRepository.save(author);
    }
    @Test
    void addUsers() {
        Optional<Role> admin = roleRepository.findByName("ADMIN");
        Optional<Role> user = roleRepository.findByName("USER");
        Optional<Role> author = roleRepository.findByName("AUTHOR");
        List<Role> adminRole = List.of(admin.get(), user.get(), author.get());
        List<Role> userRole = List.of(user.get(), author.get());
        List<Role> authorRole = List.of(author.get());
        List<TokenConfirm> tokenConfirms = new ArrayList<>();
        User user1 = new User(1, "admin 1", "abc1@gmail.com", passwordEncoder.encode("123"), false, adminRole, tokenConfirms);
        userRepository.save(user1);
        User user2 = new User(2, "user 2", "abc2@gmail.com", passwordEncoder.encode("123"), false, userRole, tokenConfirms);
        userRepository.save(user2);
        User user3 = new User(3, "author 3", "abc3@gmail.com", passwordEncoder.encode("123"), false, authorRole, tokenConfirms);
        userRepository.save(user3);
    }

}
