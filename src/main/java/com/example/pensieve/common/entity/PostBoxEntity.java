package com.example.pensieve.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="post_box")
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper = true)
@DynamicInsert
@DynamicUpdate
public class PostBoxEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long postId;

//    @JsonIgnore
//    public void setPostId(Long postId){
//        this.postId = postId;
//    }
//
//    @Check(constraints = "postId>0")
//    @JsonIgnore
//    public Long getPostId(){
//        return postId;
//    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(nullable = false)
//    ,columnDefinition = "Integer default 0")
//    @ColumnDefault("0")
    private Character banYn;
    //삭제여부 : N=삭제안됨 Y=삭제됨

    @PrePersist
    void defaultValues(){
        this.banYn = this.banYn== null ? 'N' : this.banYn;
        this.hits = this.hits==null ? 0L : this.hits;
        this.likes = this.likes==null ? 0L :this.likes;
    }

    @Column(nullable = false)
//    @ColumnDefault("0")
    private Long hits;
    //조회수

    @Column(nullable = false)
//    @ColumnDefault("0")
    private Long likes;


    @Column(length = 100)
    @NotNull
    private String ctnt;


}
