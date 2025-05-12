package com.example.SpringWithPostgreSQL.controller;

import com.example.SpringWithPostgreSQL.dto.UserDto;
import com.example.SpringWithPostgreSQL.entity.User;
import com.example.SpringWithPostgreSQL.mapper.UserMapper;
import com.example.SpringWithPostgreSQL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        userService.register(userDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        userService.verify(userDto);
        System.out.println(userService.verify(userDto));
        return ResponseEntity.ok("User login successfully");

    }
}
