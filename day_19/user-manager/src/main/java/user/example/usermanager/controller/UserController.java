package user.example.usermanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import user.example.usermanager.dto.UserDto;
import user.example.usermanager.model.response.FileResponse;
import user.example.usermanager.request.*;
import user.example.usermanager.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("users")
    public List<UserDto> getAllUser(){
        return userService.getAllUser();
    }

    //http://localhost:8080/api/v1/search?name={nameValue}
    @GetMapping("search")
    public List<UserDto> getUserByNameContainIgnoreCase(@RequestParam String name) {
        return userService.getUserByNameContainIgnoreCase(name);
    }

    //http://localhost:8080/api/v1/users/{id}
    @GetMapping("users/{id}")
    public UserDto getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    //http://localhost:8080/api/v1/users
    @PostMapping("users")
    public UserDto createUser(@Valid @RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    //http://localhost:8080/api/v1/users/{id}
    @PutMapping("users/{id}")
    public UserDto updateUser(@PathVariable int id,@Valid @RequestBody UpdateUserRequest request){
        return userService.updateUser(id, request);
    }

    //http://localhost:8080/api/v1/users/{id}/update-avatar
    @PutMapping("users/{id}/update-avatar")
    public FileResponse updateUserAvatar(@PathVariable int id, @Valid @ModelAttribute("file")MultipartFile file){
        return userService.updateUserAvatar(id, file);
    }
    // http://localhost:8080/api/v1/users/{id}/update-password
    @PutMapping("users/{id}/update-password")
    public UserDto updateUserPassword(@PathVariable int id,@Valid @RequestBody UpdateUserPasswordRequest request){
        return userService.updateUserPassword(id, request);
    }
    // http://localhost:8080/api/v1/users/{id}/fotgot-password
    @GetMapping("users/{id}/fotgot-password")
    public String updateUserPasswordForgot(@PathVariable int id){
        return userService.updateUserPasswordForgot(id);
    }

    //http://localhost:8080/api/v1/users/{id}
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }
}
