package com.example.firstapp.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.firstapp.dto.UserDto;
import com.example.firstapp.repository.UserRepository;
import com.example.firstapp.request.*;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public List<UserDto> getAllUser() {
        return userRepository.getAllUser();
    }

    public List<UserDto> getUserByNameContainIgnoreCase(String name) {
        return userRepository.getUserByNameContainIgnoreCase(name);
    }

    public UserDto getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public UserDto createUser(CreateUserRequest request) {
        return userRepository.createUser(request);
    }

    public UserDto updateUser(int id, UpdateUserRequest request) {
        return userRepository.updateUser(id, request);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public UserDto updateUserAvatar(int id, UpdateUserAvatarRequest request) {
        return userRepository.updateUserAvatar(id, request);
    }

    public UserDto updateUserPassword(int id, UpdateUserPasswordRequest request) {
        return userRepository.updateUserPassword(id, request);
    }

    public UserDto updateUserPasswordForgot(int id, UpdateUserPasswordForgotRequest request) {
        return userRepository.updateUserPasswordForgot(id, request);
    }
}
