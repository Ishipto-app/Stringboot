package com.example.blogbackend.controller;

import com.example.blogbackend.dto.UserDto;
import com.example.blogbackend.entity.Category;
import com.example.blogbackend.entity.Image;
import com.example.blogbackend.entity.Role;
import com.example.blogbackend.entity.User;
import com.example.blogbackend.request.CreateUserRequest;
import com.example.blogbackend.request.UpdateUserPasswordRequest;
import com.example.blogbackend.request.UpdateUserRequest;
import com.example.blogbackend.response.FileResponse;
import com.example.blogbackend.service.FileService;
import com.example.blogbackend.service.UserAdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public Page<UserDto> getAllUsers(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return userAdminService.getAllUsersByPageAndPagesize(page, pageSize);
    }
    @GetMapping("users/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        return userAdminService.getUserById(id);
    }
    @GetMapping("users/roles")
    public List<Role> getUserRole(){
        return userAdminService.getUserRole();
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
    public UserDto updateUser(@PathVariable Integer id,@Valid @RequestBody UpdateUserRequest request){
        System.out.println("Vao 4");
        return userAdminService.updateUser(id, request);
    }

    //http://localhost:8080/api/v1/users/{id}/update-avatar
    @PutMapping("users/{id}/update-avatar")
    public FileResponse updateUserAvatar(HttpServletRequest httpRequest, @PathVariable int id, @Valid @ModelAttribute("file") MultipartFile file){
        return userAdminService.updateUserAvatar(httpRequest, id, file);
    }
    // http://localhost:8080/api/v1/users/{id}/update-password
    @PutMapping("users/{id}/update-password")
    public UserDto updateUserPassword(@PathVariable int id,@Valid @RequestBody UpdateUserPasswordRequest request){
        return userAdminService.updateUserPassword(id, request);
    }
    // http://localhost:8080/api/v1/users/{id}/fotgot-password
    @GetMapping("users/{id}/fotgot-password")
    public String updateUserPasswordForgot(@PathVariable int id){
        return userAdminService.updateUserPasswordForgot(id);
    }
}
