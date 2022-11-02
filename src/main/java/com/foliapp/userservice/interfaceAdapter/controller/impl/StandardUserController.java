package com.foliapp.userservice.interfaceAdapter.controller.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.foliapp.userservice.domain.User;
import com.foliapp.userservice.fakes.UserDatabase;
import com.foliapp.userservice.interfaceAdapter.controller.UserController;
import com.foliapp.userservice.interfaceAdapter.repository.UserRepository;
import com.foliapp.userservice.mapper.UserMapper;
import com.foliapp.userservice.useCase.UserUseCase;
import com.foliapp.userservice.web.resource.UserResource;

@RequestScoped
@Transactional
public class StandardUserController implements UserController {

    @Inject
    UserUseCase userUserCase;

    @Inject
    UserMapper userMapper;

    @Inject
    UserRepository userRepository;

    @Override
    public UserResource saveUser(UserResource user) {
        User userDomain = userMapper.fromResourceToDomain(user);

        User validatedUser = userUserCase.saveUser(userDomain);

        User savedUser = userRepository.save(validatedUser);

        return userMapper.fromDomainToResource(savedUser);
    }

    @Override
    public UserResource logInUser(String email, String password) {
        // TODO add real implementation
        for (User userData : UserDatabase.getInstance().users) {
            if (email.equals(userData.getEmail()) && password.equals(userData.getPassword())) {
                return userMapper.fromDomainToResource(userData);
            }
        }
        return null;
    }

}
