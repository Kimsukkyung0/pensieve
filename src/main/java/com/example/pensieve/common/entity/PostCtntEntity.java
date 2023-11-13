//package com.example.pensieve.common.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import lombok.experimental.SuperBuilder;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//@Entity
//@Table(name="post_ctnt")
//@Data
//@NoArgsConstructor
//public class PostCtntEntity {
//
//    @Id
//    @OneToOne
//    @JoinColumn(name = "post_id")
//    private PostBoxEntity postBoxEntity;
//
//    @Column
//    private String styleCodeList;
//    //혹은 이후에 이미지 url로 대체해도 될듯.
//}
