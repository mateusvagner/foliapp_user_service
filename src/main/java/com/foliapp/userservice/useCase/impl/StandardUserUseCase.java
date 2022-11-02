package com.foliapp.userservice.useCase.impl;

import com.foliapp.userservice.domain.User;
import com.foliapp.userservice.useCase.UserUseCase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class StandardUserUseCase implements UserUseCase {
    @Override
    public User saveUser(User user) {
        if (user.getName().length() < 3) {
            throw new RuntimeException("Nome deve ter mais que tres caracteres");
        }
        return user;
    }

    @Override
    public User logInUser(User user) {
        return null;
    }
}
