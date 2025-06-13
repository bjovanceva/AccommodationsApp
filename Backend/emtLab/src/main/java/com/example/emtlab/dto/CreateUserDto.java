package com.example.emtlab.dto;

import com.example.emtlab.model.domain.User;
import com.example.emtlab.model.enumerations.Role;

public record CreateUserDto(String username, String password, String repeatPassword, String name, String surname, Role role) {

    public boolean isPasswordMatching() {
        return password.equals(repeatPassword);
    }
    public User toUser(){
        return new User(username, password, name, surname, role);
    }
}
