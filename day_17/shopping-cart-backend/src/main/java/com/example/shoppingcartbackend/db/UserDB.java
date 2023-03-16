package com.example.shoppingcartbackend.db;

import com.example.shoppingcartbackend.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> users = new ArrayList<>(List.of(
            new User(1, "Nguyễn Thu Hằng", "thuhangvnua@gmail.com", "0123456789", null),
            new User(2, "Bùi Phương Loan", "hien@techmaster.vn", "0123456789", null)
    ));
}
