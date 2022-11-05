package com.foliapp.userservice.data.dao;

import com.foliapp.userservice.data.entity.UserEntity;

public interface UserDao {
    UserEntity save(UserEntity user);

    UserEntity getByEmail(String email);

}
