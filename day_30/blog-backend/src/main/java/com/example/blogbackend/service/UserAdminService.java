package com.example.blogbackend.service;

import com.example.blogbackend.dto.BlogDto;
import com.example.blogbackend.dto.UserDto;
import com.example.blogbackend.entity.*;
import com.example.blogbackend.entity.User;
import com.example.blogbackend.exception.BadRequestException;
import com.example.blogbackend.exception.NotFoundException;
import com.example.blogbackend.mapper.BlogMapper;
import com.example.blogbackend.mapper.UserMapper;
import com.example.blogbackend.repository.*;
import com.example.blogbackend.request.CreateUserRequest;
import com.example.blogbackend.request.UpdateUserPasswordRequest;
import com.example.blogbackend.request.UpdateUserRequest;
import com.example.blogbackend.response.FileResponse;
import com.example.blogbackend.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    @Autowired
    private FileService fileService;
    @Autowired
    private MailService mailService;

    public Page<UserDto> getAllUsersByPageAndPagesize(Integer page, Integer pageSize) {

        Page<User> userPage = userAdminRepository.findAll(PageRequest.of(page - 1, pageSize));
        Page<UserDto> usersDtoPage = userPage.map(user -> UserMapper.toUserDto(user));
        return usersDtoPage;
    }

    public User createUser(CreateUserRequest request) {
        Optional<User> optional = userAdminRepository.findByEmail(request.getEmail());
        if(optional.isPresent()){
            throw new BadRequestException("User da ton tai");
        }
        List<Role> roles = roleRepository.findAllById(request.getRoles());
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        return userAdminRepository.save(user);
    }

    public UserDto updateUser(Integer id, UpdateUserRequest request) {
        System.out.println("User id 2" + id);
        System.out.println(request.getName());
        System.out.println(request.getEmail());
        Optional<User> optional = userAdminRepository.findById(id);
        if(optional.isPresent()){
            System.out.println("User id 3" + id);
            System.out.println(request.getRoles());
            List<Role> roles = roleRepository.findAllById(request.getRoles());
            User user = optional.get();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setRoles(roles);
            userAdminRepository.save(user);
            return UserMapper.toUserDto(user);
        }
        throw new NotFoundException("Not found user with id = " + id);
    }

    public UserDto getUserById(Integer id) {
        Optional<User> optional = userAdminRepository.findById(id);
        if(optional.isPresent()){
            return UserMapper.toUserDto(optional.get());
        }
        throw new NotFoundException("Not found user with id = " + id);
    }

    public List<Role> getUserRole() {
        return roleRepository.findAll();
    }

    public ResponseEntity<?> uploadFile(HttpServletRequest httpRequest, @ModelAttribute("file") MultipartFile file){
        FileResponse fileResponse = fileService.uploadFile(httpRequest, file);
        return ResponseEntity.ok(fileResponse);
    }
    public FileResponse updateUserAvatar(HttpServletRequest httpRequest, int id, MultipartFile file) {

        // upload file
        FileResponse fileResponse = fileService.uploadFile(httpRequest, file);
        Optional<User> optional = userAdminRepository.findById(id);
        if(optional.isPresent()){
            User user = optional.get();
            user.setAvatar(fileResponse.getUrl());
            userAdminRepository.save(user);
            return fileResponse;
        }
        throw new NotFoundException("Not found user with id = " + id);

    }

    public UserDto updateUserPassword(int id, UpdateUserPasswordRequest request) {
        Optional<User> userOptional = userAdminRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            System.out.println("Vao10");
            if(passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                userAdminRepository.save(user);
                return UserMapper.toUserDto(user);
            }
            throw new BadRequestException("old password khong dung");
        }
        throw new BadRequestException("không có user với id = " + id);
    }

    public String updateUserPasswordForgot(int id) {
        // ktra xem mail co ton tai hay ko
        Optional<User> userOptional = userAdminRepository.findById(id);
        if(userOptional.isPresent()) {

            //create new pass
            Random rd = new Random();
            String newPassword = String.valueOf(rd.nextInt(900) + 100);
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userAdminRepository.save(user);

            //gui mail
            mailService.sendMail(
                    user.getEmail(),
                    "Quen mat khau",
                    "Mat khau moi " + newPassword
            );

            return newPassword;
        }
        throw new BadRequestException("không có user với id = " + id);
    }
}
