package user.example.usermanager.mapper;

import user.example.usermanager.dto.UserDto;
import user.example.usermanager.entity.User;

public class UserMapper {
    public static UserDto userDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getAddress(), user.getAvatar());
    }
}
