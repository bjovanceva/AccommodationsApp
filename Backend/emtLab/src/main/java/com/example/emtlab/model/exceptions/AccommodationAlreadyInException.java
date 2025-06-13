package com.example.emtlab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class AccommodationAlreadyInException extends RuntimeException {

    public AccommodationAlreadyInException(Long id, String username) {
        super(String.format("Accommodation with id: %d already exists in temporary list for user with username %s", id, username));
    }
}
