package user.example.usermanager.db;

import user.example.usermanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {

     public static List<User> users = new ArrayList<>(List.of(
            new User(1, "Bùi Hiên", "buihien01091997@gmail.com", "0344005816", "Tỉnh Thái Bình", null, "1111111"),
            new User(2, "Nguyễn Thu Hằng", "thuhangvnua@gmail.com", "0123456789", "Tỉnh Nam Định", null, "2222222"),
            new User(3, "Bùi Phương Loan", "hien@techmaster.vn", "0123456789", "Tỉnh Hưng Yên", null, "333333")
     ));
}
