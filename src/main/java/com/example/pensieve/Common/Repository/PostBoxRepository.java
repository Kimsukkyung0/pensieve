package com.example.pensieve.Common.Repository;

import com.example.pensieve.Common.Entity.PostBoxEntity;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostBoxRepository extends JpaRepository<PostBoxEntity, Long> {
}
