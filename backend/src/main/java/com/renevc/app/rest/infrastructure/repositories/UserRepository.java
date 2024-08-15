package com.renevc.app.rest.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renevc.app.rest.infrastructure.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByEmailAndPassword(String email, String password);
}
