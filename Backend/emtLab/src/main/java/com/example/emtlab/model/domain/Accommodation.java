package com.example.emtlab.model.domain;

import com.example.emtlab.model.enumerations.AccommodationType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;

@Data
@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AccommodationType category;

    @ManyToOne
    private Host host;

//    @ManyToOne
//    private User user;

    private Integer numRooms;

    private boolean isRented;


    public Accommodation() {}

    public Accommodation(String name, AccommodationType category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented=false;
    }

    public Accommodation(String name, AccommodationType category, Host host, Integer numRooms, boolean isRented) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented=isRented;
    }


    public void setName(String name){
        this.name=name;
    }

    public void setCategory(AccommodationType category){
        this.category=category;
    }

    public void setHost(Host host){
        this.host=host;
    }

    public void setNumRooms(Integer numRooms){
        this.numRooms=numRooms;
    }

    public void setRented(boolean isRented){
        this.isRented=isRented;
    }

    public String getName(){
        return this.name;
    }

    public AccommodationType getCategory(){
        return this.category;
    }

    public Host getHost(){
        return this.host;
    }

    public Integer getNumRooms(){
        return this.numRooms;
    }

    public Long getId() {
        return id;
    }

    public boolean getRented(){
        return this.isRented;
    }
}
