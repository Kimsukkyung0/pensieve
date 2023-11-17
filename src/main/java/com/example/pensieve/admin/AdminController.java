package com.example.pensieve.admin;


import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
public class AdminController {

    //Todo 신고하기
    private AdminService service;
    @PatchMapping
    public int banPost(@RequestParam Long postId){
        return service.banPost(postId);
    }

    //Todo 신고당한 게시물리스트

}
