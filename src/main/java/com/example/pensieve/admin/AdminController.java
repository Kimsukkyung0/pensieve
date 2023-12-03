package com.example.pensieve.admin;


import com.example.pensieve.common.entity.PostBoxEntity;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/admin")
@AllArgsConstructor
public final class AdminController {

    private final AdminService service;

    @PatchMapping
    @Operation(summary = "신고게시물 확인후 차단처리" )
    public int banPost(@RequestParam Long postId){
        return service.banPost(postId);
    }

    @PatchMapping("/{postId}")
    @Operation(summary = "차단처리취소" )
    public int banPostRevert(@RequestParam@PathVariable Long postId){
        return service.banPostRev(postId);
    }

    @GetMapping("/list")
    @Operation(summary = "신고게시물 리스트" )
    public List<PostBoxEntity> getReportedPost(){
        return service.getReportedPosts();
    }

}
