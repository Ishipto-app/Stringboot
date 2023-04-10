package com.example.exercise_security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/")
    public ResponseEntity<?> getHome(){
        return ResponseEntity.ok("Home");
    }

    @Secured({"ROLE_ADMIN", "ROLE_SALE"})
    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(){
        return ResponseEntity.ok("Dashboard");
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/user")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok("User");
    }

    @Secured({"ROLE_ADMIN", "ROLE_SALE"})
    @GetMapping("/category")
    public ResponseEntity<?> getCategory (){
        return ResponseEntity.ok("Category ");
    }

    @Secured({"ROLE_ADMIN", "ROLE_SALE"})
    @GetMapping("/product")
    public ResponseEntity<?> getProduct(){
        return ResponseEntity.ok("Product");
    }

    @Secured({"ROLE_ADMIN", "ROLE_SALE"})
    @GetMapping("/brand")
    public ResponseEntity<?> getBrand(){
        return ResponseEntity.ok("Brand");
    }

    @Secured({"ROLE_ADMIN", "ROLE_SALE"})
    @GetMapping("/order")
    public ResponseEntity<?> getOrder(){
        return ResponseEntity.ok("Order");
    }

    @Secured({"ROLE_ADMIN", "ROLE_SALE", "ROLE_AUTHOR"})
    @GetMapping("/author")
    public ResponseEntity<?> getAuthor(){
        return ResponseEntity.ok("Author");
    }

    @Secured({"ROLE_ADMIN", "ROLE_SALE", "ROLE_AUTHOR"})
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication){
        return ResponseEntity.ok(authentication);
    }
}
