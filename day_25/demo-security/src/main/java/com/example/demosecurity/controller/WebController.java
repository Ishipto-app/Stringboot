package com.example.demosecurity.controller;

import com.example.demosecurity.annotation.IsAuthor;
import com.example.demosecurity.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {
    @Autowired
    AuthenticationFacade authenticationFacade;

    @GetMapping("/")
    public ResponseEntity<?> getHome(){
        return ResponseEntity.ok("Home");
    }

    //@PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("user")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok("User");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin")
    public ResponseEntity<?> getAdmin(){
        return ResponseEntity.ok("Admin");
    }

    //@PreAuthorize("hasRole('AUTHOR')")
    //@Secured("ROLE_AUTHOR")
    @IsAuthor
    @GetMapping("author")
    public ResponseEntity<?> getAuthor(){
        return ResponseEntity.ok("Author");
    }

    @IsAuthor
    @GetMapping("user-info")
    public ResponseEntity<?> getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }
    @GetMapping("user-info-1")
    public ResponseEntity<?> getUserInfo1(Principal principal){
        return ResponseEntity.ok(principal);
    }
    //
    @GetMapping("user-info-2")
    public ResponseEntity<?> getUserInfo2(Authentication authentication){
        return ResponseEntity.ok(authentication);
    }
    @GetMapping("user-info-3")
    public ResponseEntity<?> getUserInfo3(){
        return ResponseEntity.ok(authenticationFacade.getAuthentication());
    }
}
