package com.example.pensieve.Common.Repository;

import com.example.pensieve.Common.Entity.ServiceAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceAdminRepository extends JpaRepository<ServiceAdminEntity,Long> {
}
