package com.example.pensieve.board;

import com.example.pensieve.board.model.PostDetailRes;
import com.example.pensieve.board.model.PostInsDto;
import com.example.pensieve.common.security.AuthenticationFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/post",
        produces = "application/json",
        method=RequestMethod.POST)
public class PostController {

    private final PostService service;

    //Todo 좋아요 올리기
    //Todo 신고하기


    @PostMapping
    public int insertPost(@RequestBody PostInsDto dto){
        if(dto!=null){
            return service.insPost(dto);
        }
        else{
            return 0;
        }
    }

//    @PostMapping("/rmd")
//    public

    @PatchMapping
    public int delPost(@RequestParam Long postId){
        return service.delPost(postId);
    }

    @PatchMapping("/like")
    public int postLikeBtn(@RequestParam Long postId){
        return service.postLikeBtn(postId);
    }

    @GetMapping
    public PostDetailRes getPostDetail(){
        return service.getPostDetail();
    }


}
