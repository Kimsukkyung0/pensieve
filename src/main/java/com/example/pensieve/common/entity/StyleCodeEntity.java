package com.example.pensieve.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="style_code")
@Data
@NoArgsConstructor
public class StyleCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique=true)
    private String uniCode;

    @Column
    private String designUrl;

    //문자마다 디자인을 저장할 url을 갖고있어야함!
    //시험적으로 두가지의 디자인을 사용할 건데, 이 중 코드로 구분하여 프론트로 보내면..
    //sprite 기술 사용.

}
