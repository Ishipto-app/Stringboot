package login.example.applogin.controller;

import jakarta.validation.Valid;
import login.example.applogin.service.UserService;
import login.example.applogin.model.User;
import login.example.applogin.request.CheckUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> checkLogin(@Valid @RequestBody CheckUserRequest request) {

        User user = userService.checkUserServer(request);
        return ResponseEntity.ok(user);
    }
}
