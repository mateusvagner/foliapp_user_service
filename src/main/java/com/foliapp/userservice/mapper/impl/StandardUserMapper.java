package com.foliapp.userservice.mapper.impl;

import javax.enterprise.context.RequestScoped;

import com.foliapp.userservice.data.entity.UserEntity;
import com.foliapp.userservice.domain.Role;
import com.foliapp.userservice.domain.User;
import com.foliapp.userservice.mapper.UserMapper;
import com.foliapp.userservice.web.resource.UserResource;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class StandardUserMapper implements UserMapper {

    @Override
    public UserResource fromDomainToResource(User user) {
        UserResource userResource = new UserResource();
        userResource.setName(user.getName());
        userResource.setEmail(user.getEmail());
        userResource.setPassword(user.getPassword());
        userResource.setRoles(this.getRolesString(user.getRoles()));

        return userResource;
    }

    @Override
    public User fromResourceToDomain(UserResource user) {
        User userDomain = new User();
        userDomain.setName(user.getName());
        userDomain.setEmail(user.getEmail());
        userDomain.setPassword(user.getPassword());
        userDomain.setRoles(getRoles(user.getRoles()));

        return userDomain;
    }

    @Override
    public UserEntity fromDomainToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRoles(this.getRolesString(user.getRoles()));

        return userEntity;
    }

    @Override
    public User fromEntityToDomain(UserEntity user) {
        User userDomain = new User();
        userDomain.setName(user.getName());
        userDomain.setEmail(user.getEmail());
        userDomain.setPassword(user.getPassword());
        userDomain.setRoles(getRoles(user.getRoles()));

        return userDomain;
    }

    private List<String> getRolesString(List<Role> roles) {
        List<String> rolesString = new ArrayList<>();

        for (Role role : roles) {
            rolesString.add(role.name());
        }

        return rolesString;
    }

    private List<Role> getRoles(List<String> rolesString) {
        List<Role> roles = new ArrayList<>();

        for (String roleString : rolesString) {
            try {
                roles.add(Role.valueOf(roleString));
            } catch (IllegalArgumentException e) {
                // left empty on purpose
            }
        }

        return roles;
    }

}
