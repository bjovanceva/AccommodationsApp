package com.example.emtlab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String continent;


    public Country() {}

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setContinent(String continent){
        this.continent=continent;
    }

    public String getName(){
        return this.name;
    }

    public String getContinent(){
        return this.continent;
    }

    public Long getId() {
        return id;
    }
}
