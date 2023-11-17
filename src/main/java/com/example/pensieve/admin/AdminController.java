package com.example.pensieve.admin;


import com.example.pensieve.common.entity.PostBoxEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/admin")
public class AdminController {

    private AdminService service;

    @PatchMapping
    public int banPost(@RequestParam Long postId){
        return service.banPost(postId);
    }

    //Todo 신고당한 게시물리스트

    @GetMapping("/list")
    public List<PostBoxEntity> getReportedPost(){
        return service.getReportedPosts();
    }

}
