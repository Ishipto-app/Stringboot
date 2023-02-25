package com.example.demo.service;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserDto;
import com.example.demo.model.UserServer;
import com.example.demo.model.User;
import com.example.demo.request.CheckUserRequest;
import com.example.demo.request.LoginRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>(List.of(
            new User(1, "abc1", "abc1@gmail.com", "abcd11", "abcabc1"),
            new User(2, "abc2", "abc2@gmail.com", "abcd12", "abcabc2"),
            new User(3, "abc3", "abc1@gmail.com", "abcd13", "abcabc3")
    ));



    public UserDto login(LoginRequest request){
        ModelMapper modelMapper = new ModelMapper();
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getUsername().equals(request.getUsername())
                    && user.getPassword().equals(request.getPassword()))
                .findFirst();
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return modelMapper.map(user, UserDto.class);
        }
        throw new BadRequestException("username hoặc password chưa chính xác");
//        return  users.stream()
//                .filter(user -> user.getUsername().equals(request.getUsername())
//                    && user.getPassword().equals(request.getPassword()))
//                .findFirst()
//                .orElseThrow(() -> {
//                    throw new BadRequestException("username hoặc password chưa chính xác");
//                });
    }


//    public User checkUserServer(CheckUserRequest request){
//        for (UserServer user: users) {
//            if(Objects.equals(user.getUsername(), request.getUsername()) && Objects.equals(user.getPassword(), request.getPassword())) {
//                User newUser = new User(user.getUsername(), user.getEmail(), user.getAvatar());
//                return newUser;
//            }
//        }
//        throw new NotFoundException("username hoặc password chưa chính xác");
//    }
}
