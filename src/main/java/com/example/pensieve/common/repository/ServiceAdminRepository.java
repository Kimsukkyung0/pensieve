package com.example.pensieve.common.repository;

import com.example.pensieve.common.entity.ServiceAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceAdminRepository extends JpaRepository<ServiceAdminEntity,Long> {
}
