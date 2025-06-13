package com.example.emtlab.model.exceptions;

public class SomethingWentWrongException extends RuntimeException{

    public SomethingWentWrongException() {
        super("SomethingWentWrong");
    }
}
