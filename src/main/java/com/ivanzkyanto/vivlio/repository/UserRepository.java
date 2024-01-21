package com.ivanzkyanto.vivlio.repository;

import com.ivanzkyanto.vivlio.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findByNameOrEmail(String name, String email);

}
