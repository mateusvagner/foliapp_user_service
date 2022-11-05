package com.foliapp.userservice.exception;

public class UserAlreadyExistsByEmailException extends RuntimeException {
    public UserAlreadyExistsByEmailException(String email) {
        super("O e-mail " + email + " já existe.");
    }
}
