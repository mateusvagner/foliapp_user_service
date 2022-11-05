package com.foliapp.userservice.useCase.impl;

import com.foliapp.userservice.domain.User;
import com.foliapp.userservice.exception.NameLengthNotValidException;
import com.foliapp.userservice.useCase.UserUseCase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class StandardUserUseCase implements UserUseCase {
    @Override
    public User saveUser(User user) {
        if (user.getName().length() <= 3) {
            throw new NameLengthNotValidException(user.getName());
        }
        // TODO add roles?
        return user;
    }

}
