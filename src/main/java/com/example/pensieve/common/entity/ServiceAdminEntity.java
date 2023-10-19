package com.example.pensieve.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(name="admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ServiceAdminEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long adminId;

    public void setAdminId(Long adminId){
        this.adminId = adminId;
    }

    @Check(constraints = "adminId>0")
    public Long getAdminId(){
        return adminId;
    }

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String pw;

}
