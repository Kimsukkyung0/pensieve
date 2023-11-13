package com.example.pensieve.board;

import com.example.pensieve.board.model.PostDetailRes;
import com.example.pensieve.board.model.PostInsDto;
import com.example.pensieve.common.security.AuthenticationFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/post")
//,
//        produces = "application/json",
//        method=RequestMethod.POST)
//무슨 이유에선지 주소값에 쿼리스트링이 붙었고, parameter 값이 postid 가 나타남..
//이유가 뭐죠 ?
public class PostController {

    private final PostService service;

    //Todo 좋아요 올리기
    //Todo 신고하기

    //Todo 1 : 11.13 jamoutils 테스트 + res test
    //Todo 2 :

//    @PostMapping
//    public int insertPost(@RequestBody PostInsDto dto){
//        if(dto!=null){
////            return service.insPost(dto);
//            return service.insPostTest(dto);
//        }
//        else{
//            return 0;
//        }
//    }

    @PostMapping
    @Operation(summary = "게시물올리기" )
    public PostDetailRes insertPost(@RequestBody PostInsDto dto){
        if(dto!=null){
//            return service.insPost(dto);
            return service.insPostTest(dto);
        }
        else{
            //값이 없을때 리턴
           throw new RuntimeException("유효한 값이 아님");
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
