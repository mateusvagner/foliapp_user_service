package com.foliapp.userservice.exception;

public class NameNotValidException extends RuntimeException {
    public NameNotValidException(String name) {
        super("The name " + name + "is not valid.");
    }
}
