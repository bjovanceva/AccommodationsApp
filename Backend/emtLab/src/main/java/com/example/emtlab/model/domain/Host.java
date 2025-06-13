package com.example.emtlab.model.domain;

import com.example.emtlab.model.domain.Country;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    private Country country;


    public Host() {}

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setSurname(String surname){
        this.surname=surname;
    }

    public void setCountry(Country country){
        this.country=country;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public Country getCountry(){
        return this.country;
    }

    public Long getId() {
        return id;
    }
}


