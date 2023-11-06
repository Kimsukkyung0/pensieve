package com.example.pensieve.board;

import com.example.pensieve.board.model.PostInsDto;
import com.example.pensieve.common.security.AuthenticationFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/post")
public class PostController {

    private final PostService service;
    @PostMapping
    public int insertPost(@RequestBody PostInsDto dto){
        if(dto!=null){
            return service.insPost(dto);
        }
        else{
            return 0;
        }
    }

    @PatchMapping
    public int delPost(@RequestParam Long postId){
        return service.delPost(postId);
    }

}
