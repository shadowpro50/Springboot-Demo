package com.example.SpringWithPostgreSQL.service;

import com.example.SpringWithPostgreSQL.dto.UserDto;

public interface UserService {

    public UserDto register(UserDto userDto);

    public boolean authenticate(String username, String rawPassword);
}
