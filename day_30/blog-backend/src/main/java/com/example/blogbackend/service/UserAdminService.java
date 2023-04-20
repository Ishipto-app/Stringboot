package com.example.blogbackend.service;

import com.example.blogbackend.entity.*;
import com.example.blogbackend.entity.User;
import com.example.blogbackend.exception.BadRequestException;
import com.example.blogbackend.exception.NotFoundException;
import com.example.blogbackend.repository.*;
import com.example.blogbackend.request.CreateUserRequest;
import com.example.blogbackend.request.UpdateUserRequest;
import com.example.blogbackend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdminService {
    @Autowired
    private UserAdminRepository userAdminRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> getAllUsersByPageAndPagesize(Integer page, Integer pageSize) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        return userAdminRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public User createUser(CreateUserRequest request) {
        Optional<User> optional = userAdminRepository.findByEmail(request.getEmail());
        if(optional.isPresent()){
            throw new BadRequestException("User da ton tai");
        }
        List<Role> roles = roleRepository.findAllById(request.getRoleIds());
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        return userAdminRepository.save(user);
    }

    public User updateUser(Integer id, UpdateUserRequest request) {
        Optional<User> optional = userAdminRepository.findById(id);
        if(optional.isPresent()){
            List<Role> roles = roleRepository.findAllById(request.getRoleIds());
            User user = optional.get();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRoles(roles);
            return userAdminRepository.save(user);
        }
        throw new NotFoundException("Not found user with id = " + id);
    }

}
