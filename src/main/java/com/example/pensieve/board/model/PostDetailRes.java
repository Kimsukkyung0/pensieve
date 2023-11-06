package com.example.pensieve.board.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostDetailRes {

    private List<Character> charList;
    private Long hits;
    private Long likes;
    private LocalDateTime createdAt;

}
