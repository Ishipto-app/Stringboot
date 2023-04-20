package com.example.blogbackend.controller;

import com.example.blogbackend.entity.Image;
import com.example.blogbackend.entity.User;
import com.example.blogbackend.request.CreateUserRequest;
import com.example.blogbackend.request.UpdateUserRequest;
import com.example.blogbackend.service.FileService;
import com.example.blogbackend.service.UserAdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class UserAdminController {
    @Autowired
    UserAdminService userAdminService;
    @Autowired
    FileService fileService;

	// Lấy ds user (có phân trang, mặc định là pageSize = 10)
	// GET : api/v1/admin/users?page={page}&pageSize={pageSize}
    @GetMapping("users")
    public Page<User> getAllUsers(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        return userAdminService.getAllUsersByPageAndPagesize(page, pageSize);
    }
	// Tạo user mới
	// POST : api/v1/admin/users
    @PostMapping("users")
    public User createUser(@Valid @RequestBody CreateUserRequest request){
        return userAdminService.createUser(request);
    }
	// Cập nhật thông tin user
	// PUT : api/v1/admin/users/{id}
    @PutMapping("users/{id}")
    public User updateUser(@PathVariable Integer id,@Valid @RequestBody UpdateUserRequest request){
        return userAdminService.updateUser(id, request);
    }

}
