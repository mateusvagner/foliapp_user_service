package com.foliapp.userservice.interfaceAdapter.repository.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.foliapp.userservice.data.dao.UserDao;
import com.foliapp.userservice.data.entity.UserEntity;
import com.foliapp.userservice.domain.User;
import com.foliapp.userservice.interfaceAdapter.repository.UserRepository;
import com.foliapp.userservice.mapper.UserMapper;

import java.util.logging.Logger;

@RequestScoped
@Transactional
public class StandardUserRepository implements UserRepository {

    @Inject
    UserDao userDao;

    @Inject
    UserMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.fromDomainToEntity(user);
        UserEntity savedUser = userDao.save(userEntity);

        return userMapper.fromEntityToDomain(savedUser);
    }

    @Override
    public User getByEmail(String email) {
        UserEntity userEntity = userDao.getByEmail(email);

        return userMapper.fromEntityToDomain(userEntity);
    }

}
