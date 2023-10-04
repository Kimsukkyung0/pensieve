package com.example.pensieve.common.repository;

import com.example.pensieve.common.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<LikeEntity, Long> {
}