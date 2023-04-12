package com.example.registeruser.service;

import com.example.registeruser.entity.TokenConfirm;
import com.example.registeruser.entity.User;
import com.example.registeruser.repository.TokenConfirmRepository;
import com.example.registeruser.repository.UserRepository;
import com.example.registeruser.request.RegisterRequest;
import com.example.registeruser.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    @Autowired
    private JwtUtils jwtUtils;

    public String register(RegisterRequest request) {
        //Tìm kiếm user theo email
        Optional<User> optional = userRepository.findByEmail(request.getEmail());
        if(optional.isPresent()){
            User user = optional.get();
            if(user.isEnabled()){
                //Nếu user đó đã được kích hoạt -> Báo lỗi “Tài khoản đã được đăng ký”
                throw new RuntimeException("Tài khoản đã được đăng ký");
            } else {
                //Nếu user chưa được kích hoạt (có name và email trùng với request gửi lên) -> Sinh ra token tương ứng -> Lưu vào bảng Token
                if(Objects.equals(user.getEmail(), request.getEmail()) && Objects.equals(user.getName(), request.getName())){
                    return saveTokenConfirm(user);
                } else {
                    throw new RuntimeException("Tài khoản đăng ký name không đúng");
                }
            }

        } else {
            //Nếu không tìm thấy user -> Lưu user vào trong database -> Sinh ra token tương ứng -> Lưu vào bảng Token -> Trả về đường dẫn kích hoạt (có thể gửi đường link kích hoạt vào trong email)
            User newUser = new User(null, request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), false, new ArrayList<>(), new ArrayList<>());
            userRepository.save(newUser);
            //tra ve duong dan kick hoat
            return saveTokenConfirm(newUser);
        }
    }
    public String saveTokenConfirm(User user) {
        // Tạo Object xác thực
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        // Tạo token từ Object trên
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        TokenConfirm tokenConfirm = new TokenConfirm();
        tokenConfirm.setUser(user);
        tokenConfirm.setToken(jwtToken);
        tokenConfirm.setCreateAt(LocalDateTime.now());
        tokenConfirm.setExpiresAt(LocalDateTime.now().plusSeconds(86400000)); // token expires in 1 day
        tokenConfirmRepository.save(tokenConfirm);
        //tra ve duong dan kick hoat
        return "http://";
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
            throw new RuntimeException("Token không tồn tại");
        }
    }
}
