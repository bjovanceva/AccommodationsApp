package com.example.emtlab.service.domain.impl;

import com.example.emtlab.model.domain.User;
import com.example.emtlab.model.enumerations.Role;
import com.example.emtlab.model.exceptions.*;
import com.example.emtlab.repository.UserRepository;
import com.example.emtlab.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
      User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new InvalidUserCredentialsException();
        return user;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
        if(!password.equals(repeatPassword)) {throw new PasswordsDoNotMatchException();         }
        if(userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }
        User user=new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
}
