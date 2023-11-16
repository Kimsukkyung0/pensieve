package com.example.pensieve.admin;


import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
public class AdminController {
    private AdminService service;
    @PatchMapping
    public int delPost(@RequestParam Long postId){
        return service.delPost(postId);
    }
}
