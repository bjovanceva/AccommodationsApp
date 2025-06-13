package com.example.emtlab.dto;

import com.example.emtlab.model.enumerations.Role;

public record RegisterUserDto(String name, String surname, String username, String password, String repeatPassword, Role role) {
}
