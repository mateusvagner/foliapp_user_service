package com.foliapp.userservice.exception;

public class UserAlreadyExistsByEmailException extends RuntimeException {
    public UserAlreadyExistsByEmailException(String email) {
        super("The e-mail " + email + " already exists.");
    }
}
