package com.example.emtlab.service.domain;

import com.example.emtlab.model.domain.User;
import com.example.emtlab.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
    User findByUsername(String username);



}
