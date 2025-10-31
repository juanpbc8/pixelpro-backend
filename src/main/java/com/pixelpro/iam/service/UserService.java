package com.pixelpro.iam.service;

import com.pixelpro.iam.entity.UserEntity;
import com.pixelpro.iam.entity.enums.RoleEnum;

import java.util.Optional;

public interface UserService {
    UserEntity createUser(String email, String rawPassword, RoleEnum role);

    Optional<UserEntity> findByEmail(String email);
}
