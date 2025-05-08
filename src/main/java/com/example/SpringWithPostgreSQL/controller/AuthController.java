package com.example.SpringWithPostgreSQL.controller;

import com.example.SpringWithPostgreSQL.dto.UserDto;
import com.example.SpringWithPostgreSQL.entity.User;
import com.example.SpringWithPostgreSQL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(String username, String password){
        boolean success = userService.authenticate(username, password);
        return success ? ResponseEntity.ok("Login successful") : ResponseEntity.status(401).body("invalid credentials");
    }
}
