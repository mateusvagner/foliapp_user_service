package com.foliapp.userservice.exception;

public class NameLengthNotValidException extends RuntimeException {
    public NameLengthNotValidException(String name) {
        super("O nome informado (" + name + ") deve conter mais de três caracteres.");
    }
}
