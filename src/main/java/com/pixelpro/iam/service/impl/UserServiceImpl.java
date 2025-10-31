package com.pixelpro.iam.service.impl;

import com.pixelpro.common.exception.ConflictException;
import com.pixelpro.iam.entity.RoleEntity;
import com.pixelpro.iam.entity.UserEntity;
import com.pixelpro.iam.entity.enums.RoleEnum;
import com.pixelpro.iam.repository.RoleRepository;
import com.pixelpro.iam.repository.UserRepository;
import com.pixelpro.iam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createUser(String email, String rawPassword, RoleEnum role) {
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException("Email already in use: " + email);
        }

        RoleEntity roleEntity = roleRepository.findByRoleName(role)
                .orElseThrow(() -> new IllegalStateException("Role not found: " + role));

        UserEntity user = UserEntity.builder()
                .email(email)
                .passwordHash(passwordEncoder.encode(rawPassword))
                .enabled(true)
                .roles(Set.of(roleEntity))
                .build();

        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
