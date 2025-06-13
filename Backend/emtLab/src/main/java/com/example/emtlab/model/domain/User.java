package com.example.emtlab.model.domain;


import com.example.emtlab.model.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "hotel_users")
public class User implements UserDetails {

    @Id
    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private String surname;

    private boolean isAccountNonExpired=true;
    private boolean isAccountNonLocked=true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;



    @Enumerated(value = EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<TemporaryReservationList> temporaryReservationLists;

    public User() {
    }

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = Role.ROLE_USER;
    }

    public User(String username, String name, String surname, Role role){
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(UserDetails userDetails){
        this.username=userDetails.getUsername();
        this.password=userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return role;
    }

    public List<TemporaryReservationList> getTemporaryReservationLists() {
        return temporaryReservationLists;
    }
}
