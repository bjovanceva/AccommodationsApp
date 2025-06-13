package com.example.emtlab.service.application;

import com.example.emtlab.dto.CreateUserDto;
import com.example.emtlab.dto.DisplayUserDto;
import com.example.emtlab.dto.LoginResponseDto;
import com.example.emtlab.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<DisplayUserDto> findByUsername(String username);
}
