package com.foliapp.userservice.data.dao.impl;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import com.foliapp.userservice.data.dao.UserDao;
import com.foliapp.userservice.data.entity.UserEntity;

import com.foliapp.userservice.exception.UserAlreadyExistsByEmailException;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
@Transactional
public class PanacheUserDao implements UserDao, PanacheRepository<UserEntity> {

    @Override
    public UserEntity save(UserEntity user) {
        UserEntity userEntity = getByEmail(user.getEmail());

        if (userEntity != null) {
            throw new UserAlreadyExistsByEmailException(user.getEmail());
        }

        persist(user);

        return user;
    }

    @Override
    public UserEntity getByEmail(String email) {
        return find("email", email).firstResult();
    }
}
