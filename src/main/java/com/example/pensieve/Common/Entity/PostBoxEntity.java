package com.example.pensieve.Common.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="postbox")
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class PostBoxEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long postId;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity userEntity;

    @Column(nullable = false, columnDefinition = "char(1)")
    private int banYn;
    //삭제여부

    @Column(nullable = false, columnDefinition = "BIGINT UNSIGNED")
    @ColumnDefault(value = "0")
    private Long hits;

    @Column(nullable = false, columnDefinition = "BIGINT UNSIGNED")
    @ColumnDefault(value = "0")
    private Long likes;

    @Column(length = 100)
    @Size(min = 5,max = 100)
    private String ctnt;


}
