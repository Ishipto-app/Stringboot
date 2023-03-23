package user.example.usermanager.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import user.example.usermanager.dto.UserDto;
import user.example.usermanager.exception.BadRequestException;
import user.example.usermanager.exception.NotFoundException;
import user.example.usermanager.mapper.UserMapper;
import user.example.usermanager.entity.User;
import user.example.usermanager.model.response.FileResponse;
import user.example.usermanager.repository.UserRepository;
import user.example.usermanager.request.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final FileService fileService;

    @Autowired
    private final MailService mailService;

    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> UserMapper.userDto(user))
                .toList();
    }

    public List<UserDto> getUserByNameContainIgnoreCase(String name) {
        List<User> users = userRepository.findByNameContainsIgnoreCase(name);
        return users.stream()
                .map(user -> UserMapper.userDto(user))
                .toList();
    }

    public UserDto getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return UserMapper.userDto(user.get());
        }
        throw new NotFoundException("Not found user with id = " + id);
    }

    public UserDto createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return UserMapper.userDto(userRepository.save(user));
    }

    public UserDto updateUser(int id, UpdateUserRequest request) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User userData =  user.get();
            userData.setName(request.getName());
            userData.setPhone(request.getPhone());
            userData.setAddress(request.getAddress());
            return UserMapper.userDto(userRepository.save(userData));
        }
        throw new NotFoundException("Not found user with id = " + id);
    }

    public void deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            System.out.println("xoa user " + id);
        } else {
            throw new NotFoundException("Not found user with id = " + id);
        }
    }

    public FileResponse updateUserAvatar(int id, MultipartFile file) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            // upload file
            FileResponse fileResponse = fileService.uploadFile(file);
            //update avatar
            User userData = user.get();
            userData.setAvatar(fileResponse.getUrl());
            userRepository.save(userData);
            return fileResponse;
        }
        throw new NotFoundException("Not found user with id = " + id);

    }

    public UserDto updateUserPassword(int id, UpdateUserPasswordRequest request) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            // check old pass
            if(Objects.equals(user.get().getPassword(), request.getOldPassword())){
                User userData = user.get();
                userData.setPassword(request.getNewPassword());
                return UserMapper.userDto(userRepository.save(userData));
            }
            throw new BadRequestException("Old password khong dung");
        }
        throw new NotFoundException("Not found user with id = " + id);
    }

    public String updateUserPasswordForgot(int id) {
        // ktra xem mail co ton tai hay ko
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            //create new pass
            Random rd = new Random();
            String newPassword = String.valueOf(rd.nextInt(900) + 100);

            //dat lai password moi
            User userData = user.get();
            userData.setPassword(newPassword);
            userRepository.save(userData);
            System.out.println(userData.getPassword());

            //gui mail
            mailService.sendMail(
                    userData.getEmail(),
                    "Quen mat khau",
                    "Mat khau moi " + newPassword
            );

            return newPassword;
        }
        throw new NotFoundException("Not found user with id = " + id);

    }
}
