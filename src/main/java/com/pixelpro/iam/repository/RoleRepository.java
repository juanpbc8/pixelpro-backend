package com.pixelpro.iam.repository;

import com.pixelpro.iam.entity.RoleEntity;
import com.pixelpro.iam.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(RoleEnum roleName);
}
