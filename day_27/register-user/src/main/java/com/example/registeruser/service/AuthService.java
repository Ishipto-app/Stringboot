package com.example.registeruser.service;

import com.example.registeruser.entity.TokenConfirm;
import com.example.registeruser.entity.User;
import com.example.registeruser.repository.TokenConfirmRepository;
import com.example.registeruser.repository.UserRepository;
import com.example.registeruser.request.RegisterRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenConfirmRepository tokenConfirmRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {
        Optional<User> optional = userRepository.findByEmail(request.getEmail());

        if (optional.isPresent()) {
            User user = optional.get();
            if(user.isEnabled()){
                //Nếu user đó đã được kích hoạt -> Báo lỗi “Tài khoản đã được đăng ký”
                return "Tài khoản đã được đăng ký";
            } else {
                //Nếu user chưa được kích hoạt (có name và email trùng với request gửi lên) -> Sinh ra token tương ứng -> Lưu vào bảng Token
                if(Objects.equals(request.getEmail(), user.getEmail()) && Objects.equals(request.getName(), user.getName())){
                    return saveTokenConfirm(user);
                } else {
                    return "Tài khoản đăng ký name không đúng";
                }
            }
        }

        // Create new user's account
        User newUser = new User(null, request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), false, new ArrayList<>(), new ArrayList<>());
        userRepository.save(newUser);
        //tra ve duong dan kick hoat
        return saveTokenConfirm(newUser);


    }
    public String saveTokenConfirm(User user) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jwtToken = Jwts.builder().setSubject(user.getEmail()).signWith(key).compact();
        TokenConfirm tokenConfirm = new TokenConfirm();
        tokenConfirm.setUser(user);
        tokenConfirm.setToken(jwtToken);
        tokenConfirm.setCreateAt(LocalDateTime.now());
        tokenConfirm.setExpiresAt(LocalDateTime.now().plusSeconds(86400000)); // token expires in 1 day
        tokenConfirmRepository.save(tokenConfirm);
        //tra ve duong dan kick hoat
        return "http://localhost:8080/confirm?token=" + jwtToken;
    }
    public String confirm(String token) {
        //Tìm kiếm xem token có tồn tại không
        Optional<TokenConfirm> optional = tokenConfirmRepository.findByToken(token);
        if(optional.isPresent()){
            //Kiểm tra xem token đã được kích hoạt hay chưa (dựa vào trường confirmedAt)
            TokenConfirm tokenConfirm = optional.get();
            LocalDateTime confirmedAt = tokenConfirm.getConfirmedAt();
            if (confirmedAt != null && confirmedAt.isBefore(LocalDateTime.now())) {
                return "Token đã confirm";
            } else {
                // the token chưa confirm
                //Kiểm tra còn trong thời gian hiệu lực không hay không (dựa vào trường expiresAt)
                LocalDateTime expiresAt = tokenConfirm.getExpiresAt();
                if (expiresAt != null && expiresAt.isBefore(LocalDateTime.now())) {
                    return "Token đã hết hạn";
                } else {
                    // the token chưa hết hạn
                    //Set lại thời gian kích hoạt của token (set lại confirmedAt = thời gian hiện tại)
                    tokenConfirm.setConfirmedAt(LocalDateTime.now());

                    //Kích hoạt user (set enable = true)
                    User user = tokenConfirm.getUser();
                    user.setEnable(true);
                    userRepository.save(user);
                    return "User đã được kich hoat";
                }
            }
        } else {
            return "Token không tồn tại";
        }
    }
}
