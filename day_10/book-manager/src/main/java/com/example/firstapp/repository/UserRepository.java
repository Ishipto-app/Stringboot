package com.example.firstapp.repository;

import org.springframework.stereotype.Repository;
import com.example.firstapp.dto.UserDto;
import com.example.firstapp.exception.BadRequestException;
import com.example.firstapp.mapper.UserMapper;
import com.example.firstapp.model.User;
import com.example.firstapp.request.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.firstapp.db.UserDB.users;

@Repository
public class UserRepository {

    public List<UserDto> getAllUser() {
        return users.stream()
                .map(user -> UserMapper.userDto(user))
                .toList();
    }

    public List<UserDto> getUserByNameContainIgnoreCase(String name) {
        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(user -> UserMapper.userDto(user))
                .toList();
    }

    public UserDto getUserById(int id) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return UserMapper.userDto(user);
        }
        throw new BadRequestException("không có user với id = " + id);
    }
    public UserDto createUser(CreateUserRequest request) {
        int maxId = users.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(Integer.MIN_VALUE);
        User user = User.builder()
                .id(maxId + 1)
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .password(request.getPassword())
                .build();
        users.add(user);
        return UserMapper.userDto(user);
    }

    public UserDto updateUser(int id, UpdateUserRequest request) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setName(request.getName());
            user.setPhone(request.getPhone());
            user.setAddress(request.getAddress());
            return UserMapper.userDto(user);
        }
        throw new BadRequestException("không có user với id = " + id);
    }

    public void deleteUser(int id) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        if(userOptional.isPresent()){
            users.removeIf(user -> Objects.equals(user.getId(), id));
            System.out.println("delete success user id = " + id);
        } else {
            throw new BadRequestException("không có user với id = " + id);
        }
    }

    public UserDto updateUserAvatar(int id, UpdateUserAvatarRequest request) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setAvatar(request.getAvatar());
            return UserMapper.userDto(user);
        }
        throw new BadRequestException("không có user với id = " + id);
    }

    public UserDto updateUserPassword(int id, UpdateUserPasswordRequest request) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getPassword().equals(request.getOldPassword())){
                user.setPassword(request.getNewPassword());
                return UserMapper.userDto(user);
            }
            throw new BadRequestException("password hien tai khong dung");
        }
        throw new BadRequestException("không có user với id = " + id);
    }

    public UserDto updateUserPasswordForgot(int id, UpdateUserPasswordForgotRequest request) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setPassword(request.getPassword());
            return UserMapper.userDto(user);
        }
        throw new BadRequestException("không có user với id = " + id);
    }
}
