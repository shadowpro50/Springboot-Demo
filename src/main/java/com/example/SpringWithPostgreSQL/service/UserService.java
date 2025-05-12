package com.example.SpringWithPostgreSQL.service;

import com.example.SpringWithPostgreSQL.dto.UserDto;
import com.example.SpringWithPostgreSQL.entity.User;

public interface UserService {

    public UserDto register(UserDto userDto);

    public String verify(UserDto userDto);
}
