package com.example.pensieve.common.repository;

import com.example.pensieve.common.entity.PostBoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostBoxRepository extends JpaRepository<PostBoxEntity, Long> {
}
