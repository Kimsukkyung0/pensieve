package com.example.pensieve.Common.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="user")
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)

public class UserEntity extends BaseEntity{
    @Id
    private String userId;

    private String email;
    //unique

    private String pw;

    private String nickNm;

    private int outYn;

}
