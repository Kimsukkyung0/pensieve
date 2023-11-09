package com.example.pensieve.common.repository;

import com.example.pensieve.common.entity.ServiceAdminEntity;
import com.example.pensieve.common.entity.StyleCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleCodeRepository extends JpaRepository<StyleCodeEntity,Long> {
}
