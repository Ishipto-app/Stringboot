package login.example.applogin.service;
import login.example.applogin.exception.NotFoundException;
import login.example.applogin.model.User;
import login.example.applogin.model.UserServer;
import login.example.applogin.request.CheckUserRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private List<UserServer> users;
    public UserService(){
        users = new ArrayList<>();
        users.add(new UserServer(1, "abc1", "abc1@gmail.com", "abcd11", "abcabc1"));
        users.add(new UserServer(2, "abc2", "abc2@gmail.com", "abcd21", "abcabc2"));
        users.add(new UserServer(3, "abc3", "abc3@gmail.com", "abcd31", "abcabc3"));
    }
    public User checkUserServer(CheckUserRequest request){
        for (UserServer user: users) {
            if(Objects.equals(user.getUsername(), request.getUsername()) && Objects.equals(user.getPassword(), request.getPassword())) {
                User newUser = new User(user.getUsername(), user.getEmail(), user.getAvatar());
                return newUser;
            }
        }
        throw new NotFoundException("username hoặc password chưa chính xác");
    }
}

