package com.example.thymeleafspingsecurity.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsSerivce implements UserDetailsService {
    //tao doi tuong ma hoa pass
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    List<User> users = new ArrayList<>(List.of(
       new User(1, "ABC1", "abc1", encoder.encode("123"), List.of("USER")),
       new User(2, "ABC2", "abc2", encoder.encode("123"), List.of("ADMIN")),
       new User(2, "ABC3", "abc3", encoder.encode("123"), List.of("USER"))

    ));
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Not found user");
                });
    }
}
