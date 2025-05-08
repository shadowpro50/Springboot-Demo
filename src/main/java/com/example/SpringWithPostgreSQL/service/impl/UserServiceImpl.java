package com.example.SpringWithPostgreSQL.service.impl;

import com.example.SpringWithPostgreSQL.dto.UserDto;
import com.example.SpringWithPostgreSQL.entity.User;
import com.example.SpringWithPostgreSQL.mapper.UserMapper;
import com.example.SpringWithPostgreSQL.repository.UserRepository;
import com.example.SpringWithPostgreSQL.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registeredUser = userRepository.save(user);
        return UserMapper.mapToUserDto(registeredUser);
    }

    @Override
    public boolean authenticate(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .orElse(false);

    }
}
