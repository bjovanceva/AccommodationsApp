package com.example.emtlab.service.application.impl;

import com.example.emtlab.dto.CreateUserDto;
import com.example.emtlab.dto.DisplayUserDto;
import com.example.emtlab.dto.LoginResponseDto;
import com.example.emtlab.dto.LoginUserDto;
import com.example.emtlab.model.domain.User;
import com.example.emtlab.helpers.JwtHelper;
import com.example.emtlab.service.application.UserApplicationService;
import com.example.emtlab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
//        User user=userService.login(loginUserDto.username(), loginUserDto.password());
//
//        String token = jwtHelper.generateToken(user);
//
//        return Optional.of(new LoginResponseDto(token));

        try {
            // Attempt to log in the user
            User user = userService.login(loginUserDto.username(), loginUserDto.password());

            if (user == null) {
                // Invalid credentials
                return Optional.empty();
            }

            // Generate JWT token for the authenticated user
            String token = jwtHelper.generateToken(user);

            // Return the token wrapped in a response DTO
            return Optional.of(new LoginResponseDto(token));

        } catch (Exception e) {
            // Handle any exceptions and return an empty Optional or custom error response
            return Optional.empty();
        }

    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        return Optional.of(DisplayUserDto.from(userService.register(createUserDto.username(), createUserDto.password(), createUserDto.repeatPassword(), createUserDto.name(),
                createUserDto.surname(), createUserDto.role())));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }
}
