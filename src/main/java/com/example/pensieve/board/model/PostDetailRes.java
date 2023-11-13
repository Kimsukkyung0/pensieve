package com.example.pensieve.board.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PostDetailRes {
//    private Long postId;
//    private List<Character> charList;
//    private Long hits;
//    private Long likes;
//    private LocalDate createdAt;

    private Long postId;
    private List<List<String>> list;
    private Long hits;
    private Long likes;
    private LocalDate createdAt;

}
