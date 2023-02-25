package hieu.test.coursesmanager.mapper;

import hieu.test.coursesmanager.dto.UserDto;
import hieu.test.coursesmanager.model.User;

public class UserMapper {
    public static UserDto userDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getAvatar());
    }
}
