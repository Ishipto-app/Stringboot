package user.example.usermanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import user.example.usermanager.dto.UserDto;
import user.example.usermanager.exception.BadRequestException;
import user.example.usermanager.mapper.UserMapper;
import user.example.usermanager.entity.User;
import user.example.usermanager.request.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static user.example.usermanager.db.UserDB.users;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    use methol: findAll
//    public List<UserDto> getAllUser() {
//        return users.stream()
//                .map(user -> UserMapper.userDto(user))
//                .toList();
//    }

    List<User> findByNameContainsIgnoreCase(String name);
//    public List<UserDto> getUserByNameContainIgnoreCase(String name) {
//        return users.stream()
//                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
//                .map(user -> UserMapper.userDto(user))
//                .toList();
//    }

    Optional<User> findById(int id);
//    @Query("select new user.example.usermanager.dto.UserDto(u.id, u.name, u.email, u.phone, u.address, u.avatar) from User u where u.id = ?1")
//    Optional<User> getUserById(int id);
//    public UserDto getUserById(int id) {
//        Optional<User> userOptional = users.stream()
//                .filter(user -> user.getId() == id)
//                .findFirst();
//        if(userOptional.isPresent()){
//            User user = userOptional.get();
//            return UserMapper.userDto(user);
//        }
//        throw new BadRequestException("không có user với id = " + id);
//    }

    //use methol save
//    public UserDto createUser(CreateUserRequest request) {
//        int maxId = users.stream()
//                .mapToInt(User::getId)
//                .max()
//                .orElse(Integer.MIN_VALUE);
//        User user = User.builder()
//                .id(maxId + 1)
//                .name(request.getName())
//                .email(request.getEmail())
//                .phone(request.getPhone())
//                .address(request.getAddress())
//                .password(request.getPassword())
//                .build();
//        users.add(user);
//        return UserMapper.userDto(user);
//    }

    //use methol save
//    public UserDto updateUser(int id, UpdateUserRequest request) {
//        Optional<User> userOptional = users.stream()
//                .filter(user -> user.getId() == id)
//                .findFirst();
//        if(userOptional.isPresent()){
//            User user = userOptional.get();
//            user.setName(request.getName());
//            user.setPhone(request.getPhone());
//            user.setAddress(request.getAddress());
//            return UserMapper.userDto(user);
//        }
//        throw new BadRequestException("không có user với id = " + id);
//    }

    //use methol deleteById
//    public void deleteUser(int id) {
//        Optional<User> userOptional = users.stream()
//                .filter(user -> user.getId() == id)
//                .findFirst();
//        if(userOptional.isPresent()){
//            users.removeIf(user -> Objects.equals(user.getId(), id));
//            System.out.println("delete success user id = " + id);
//        } else {
//            throw new BadRequestException("không có user với id = " + id);
//        }
//    }

    //ap dung update file image vao avatar
//    public UserDto updateUserAvatar(int id, String avatar) {
//        Optional<User> userOptional = users.stream()
//                .filter(user -> user.getId() == id)
//                .findFirst();
//
//        if(userOptional.isPresent()){
//            User user = userOptional.get();
//            user.setAvatar(avatar);
//            return UserMapper.userDto(user);
//        }
//        throw new BadRequestException("không có user với id = " + id);
//    }

    //su dung method save
//    public UserDto updateUserPassword(int id, UpdateUserPasswordRequest request) {
//        Optional<User> userOptional = users.stream()
//                .filter(user -> user.getId() == id)
//                .findFirst();
//        if(userOptional.isPresent()){
//            User user = userOptional.get();
//            if(user.getPassword().equals(request.getOldPassword())){
//                user.setPassword(request.getNewPassword());
//                return UserMapper.userDto(user);
//            }
//            throw new BadRequestException("old password khong dung");
//        }
//        throw new BadRequestException("không có user với id = " + id);
//    }

//    public User updateUserPasswordForgot(int id, String password) {
//        Optional<User> userOptional = users.stream()
//                .filter(user -> user.getId() == id)
//                .findFirst();
//        if(userOptional.isPresent()){
//            User user = userOptional.get();
//            user.setPassword(password);
//            return user;
//        }
//        throw new BadRequestException("không có user với id = " + id);
//    }
}
