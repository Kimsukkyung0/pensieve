package com.example.pensieve.common.repository;

import com.example.pensieve.common.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<LikeEntity, Long> {
    @Query("SELECT p FROM LikeEntity p WHERE p.postBoxEntity.postId = :postId AND p.userEntity.userId = :userId")
    Optional<LikeEntity> likesYn(Long postId, Long userId);
}
