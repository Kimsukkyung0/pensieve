package com.example.pensieve.Common.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="user")
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class UserEntity extends BaseEntity{
    @Id
    private String userId;

    @Column(nullable = false, length = 50, unique = true)
    @Size(min = 5, max = 50)
    private String email;
    //unique

    @JsonIgnore
    @Column(nullable = false)
    private String pw;
    //size 제약 추가할것

    @Column(nullable = false, length = 10, unique = true)
    @Size(min = 2, max = 10)
    private String nickNm;

    @Column(nullable = false, length = 1)
    @ColumnDefault(value = "0")
    private int outYn;
    //탈퇴여부

}
