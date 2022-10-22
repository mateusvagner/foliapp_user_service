package com.foliapp.userservice.mapper;

import com.foliapp.userservice.domain.User;
import com.foliapp.userservice.web.resource.UserResource;

public interface UserMapper {

    UserResource fromDomainToResource(User user);

    User fromResourceToDomain(UserResource user);
}
