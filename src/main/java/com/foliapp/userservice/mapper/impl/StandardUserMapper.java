package com.foliapp.userservice.mapper.impl;

import javax.enterprise.context.RequestScoped;

import com.foliapp.userservice.data.entity.UserEntity;
import com.foliapp.userservice.domain.User;
import com.foliapp.userservice.mapper.UserMapper;
import com.foliapp.userservice.web.resource.UserResource;

@RequestScoped
public class StandardUserMapper implements UserMapper {

    @Override
    public UserResource fromDomainToResource(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User fromResourceToDomain(UserResource user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserEntity fromDomainToEntity(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User fromEntityToDomain(UserEntity user) {
        // TODO Auto-generated method stub
        return null;
    }

}
