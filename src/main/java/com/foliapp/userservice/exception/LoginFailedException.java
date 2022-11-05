package com.foliapp.userservice.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("O e-mail informado ou a senha não estão corretos");
    }
}
