package user.example.usermanager.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import user.example.usermanager.dto.UserDto;
import user.example.usermanager.model.User;
import user.example.usermanager.model.response.FileResponse;
import user.example.usermanager.repository.UserRepository;
import user.example.usermanager.request.*;

import java.util.List;
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

    public FileResponse updateUserAvatar(int id, MultipartFile file) {

        // upload file
        FileResponse fileResponse = fileService.uploadFile(file);

        // set lai avatar
        UserDto user = userRepository.updateUserAvatar(id, fileResponse.getUrl());

        return fileResponse;
    }

    public UserDto updateUserPassword(int id, UpdateUserPasswordRequest request) {
        return userRepository.updateUserPassword(id, request);
    }

    public String updateUserPasswordForgot(int id) {
        // ktra xem mail co ton tai hay ko
        UserDto user = userRepository.getUserById(id);

        //create new pass
        Random rd = new Random();
        String newPassword = String.valueOf(rd.nextInt(900) + 100);

        //dat lai password moi
        User newUser = userRepository.updateUserPasswordForgot(id, newPassword);

        System.out.println(newUser.getPassword());

        //gui mail
        mailService.sendMail(
                newUser.getEmail(),
                "Quen mat khau",
                "Mat khau moi " + newPassword
        );

        return newPassword;
    }
}
