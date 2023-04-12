package com.example.registeruser.service;

import com.example.registeruser.entity.TokenConfirm;
import com.example.registeruser.entity.User;
import com.example.registeruser.repository.TokenConfirmRepository;
import com.example.registeruser.repository.UserRepository;
import com.example.registeruser.request.RegisterRequest;
import com.example.registeruser.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class CustomUserDetailsSerivce implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Not found user");
                });
    }

}
