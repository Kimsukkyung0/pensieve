package com.example.pensieve.Common.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity

public class PostBoxEntity extends BaseEntity{

    @Id
    private Long postId;

    @OneToOne
    private UserEntity userId;

    @NotNull
    private int banYn;

    private Long hits;

    private Long likes;

    private String ctnt;


}
