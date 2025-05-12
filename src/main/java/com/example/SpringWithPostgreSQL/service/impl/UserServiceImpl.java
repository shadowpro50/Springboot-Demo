package com.example.SpringWithPostgreSQL.service.impl;

import com.example.SpringWithPostgreSQL.dto.UserDto;
import com.example.SpringWithPostgreSQL.entity.User;
import com.example.SpringWithPostgreSQL.mapper.UserMapper;
import com.example.SpringWithPostgreSQL.repository.UserRepository;
import com.example.SpringWithPostgreSQL.service.JWTService;
import com.example.SpringWithPostgreSQL.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDto register(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registeredUser = userRepository.save(user);
        return UserMapper.mapToUserDto(registeredUser);
    }

    @Override
    public String verify(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(userDto.getUsername());
        }
        return "Fail";
    }
}
