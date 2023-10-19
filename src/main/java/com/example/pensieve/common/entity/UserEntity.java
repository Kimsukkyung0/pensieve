package com.example.pensieve.common.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="p_user")
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper = true)
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long userId;

    public void setAdminId(Long adminId){
        this.userId = userId;
    }

    @Check(constraints = "adminId>0")
    public Long getUserId(){
        return userId;
    }


    @Column(nullable = false, length = 50, unique = true)
//    @Size(min = 5, max = 50)
    private String email;
    //unique

    @JsonIgnore
    @Column(nullable = false)
    private String pw;
    //size 제약 추가할것

    @Column(nullable = false, length = 10, unique = true)
//    @Size(min = 2, max = 10)
    private String nickNm;

    @Column(nullable = false, length = 1)
    @ColumnDefault(value = "0")
    private int outYn;
    //탈퇴여부

}
