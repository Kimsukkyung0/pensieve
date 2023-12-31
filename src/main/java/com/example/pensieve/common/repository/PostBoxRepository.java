package com.example.pensieve.common.repository;

import com.example.pensieve.common.entity.PostBoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostBoxRepository extends JpaRepository<PostBoxEntity, Long> {
    @Query("SELECT p.postId FROM PostBoxEntity p WHERE p.banYn = 'N'")
    List<Long> findAllPostId();

    @Query("SELECT p FROM PostBoxEntity p WHERE p.banYn = 'N'AND p.reportCnt >0 ORDER BY p.createdAt desc")
    List<PostBoxEntity> findPostsByReportButNotBanned(Long reportCnt);
}
