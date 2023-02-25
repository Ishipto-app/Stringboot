package hieu.test.coursesmanager.mapper;
import hieu.test.coursesmanager.dto.CourseDto;
import hieu.test.coursesmanager.dto.UserDto;
import hieu.test.coursesmanager.exception.BadRequestException;
import hieu.test.coursesmanager.model.Course;
import hieu.test.coursesmanager.model.User;

import java.util.Objects;
import java.util.Optional;

import static hieu.test.coursesmanager.db.UserDB.users;

public class CourseMapper {
    public static CourseDto courseDto(Course course){
        Optional<User> userOptional = users.stream()
                .filter(user -> Objects.equals(user.getId(), course.getUserId()))
                .findFirst();
        if(userOptional.isPresent()){
            UserDto userDto = UserMapper.userDto(userOptional.get());
            return new CourseDto(course.getId(), course.getName(), course.getDescription(), course.getType(), course.getTopics(), course.getThumbnail(), userDto);
        }
        throw new BadRequestException("không có user với id = " + course.getUserId());
    }
}
