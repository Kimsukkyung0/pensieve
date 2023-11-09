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
    @Column(nullable = false)
    private Long utf8Code;

    @Column
    private String designUrl;

    //문자마다 디자인을 저장할 url을 갖고있어야함!
}
