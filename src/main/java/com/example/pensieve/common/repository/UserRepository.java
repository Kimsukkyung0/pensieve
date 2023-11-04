package com.example.pensieve.common.repository;

import com.example.pensieve.common.entity.UserEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT n.email FROM UserEntity n WHERE n.email = :eaddress")
    Optional<UserEntity> findByEmail(String eaddress);

}
