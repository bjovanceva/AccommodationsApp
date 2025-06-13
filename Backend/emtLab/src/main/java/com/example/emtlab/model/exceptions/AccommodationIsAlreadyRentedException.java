package com.example.emtlab.model.exceptions;

public class AccommodationIsAlreadyRentedException extends RuntimeException{
    public AccommodationIsAlreadyRentedException() {
        super("The accommodation is already rented");
    }
}
