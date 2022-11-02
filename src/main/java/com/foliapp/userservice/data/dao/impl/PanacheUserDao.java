package com.foliapp.userservice.data.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import com.foliapp.userservice.data.dao.UserDao;
import com.foliapp.userservice.data.entity.UserEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
@Transactional
public class PanacheUserDao implements UserDao, PanacheRepository<UserEntity> {

    @Override
    public UserEntity save(UserEntity user) {
        // TODO Check if email already exists
        persist(user);
        return user;
    }
}
