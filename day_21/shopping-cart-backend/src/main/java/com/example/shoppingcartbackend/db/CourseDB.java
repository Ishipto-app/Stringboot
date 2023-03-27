package com.example.shoppingcartbackend.db;

import com.example.shoppingcartbackend.model.CartItem;
import com.example.shoppingcartbackend.model.Category;
import com.example.shoppingcartbackend.model.Course;

import java.util.ArrayList;
import java.util.List;


public class CourseDB {
    public static List<Course> courses = new ArrayList<>(List.of(
            new Course(1, "name 1", "description 1", "type 1", List.of(new Category(1, "frontend"), new Category(2, "backend")), "https://images.unsplash.com/photo-1523381294911-8d3cead13475?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTh8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60", 300000, 1.0, 1),
            new Course(2, "name 2", "description 2", "type 2", List.of(new Category(1, "frontend"), new Category(2, "backend")), "https://images.unsplash.com/photo-1612423284934-2850a4ea6b0f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjl8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60", 400000, 2.0, 1),
            new Course(3, "name 3", "description 3", "type 3", List.of(new Category(1, "frontend"), new Category(2, "backend")), "https://images.unsplash.com/photo-1564584217132-2271feaeb3c5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzJ8fGNsb3RoZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60", 500000, 3.0, 2)
    ));
}
