package com.example.thymeleafspingsecurity.security;

import com.example.thymeleafspingsecurity.entity.User;
import com.example.thymeleafspingsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsSerivce implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsSerivce(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Not found user");
                });
    }
}
