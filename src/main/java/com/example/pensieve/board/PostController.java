package com.example.pensieve.board;

import com.example.pensieve.board.model.PostDetailRes;
import com.example.pensieve.board.model.PostInsDto;
import com.example.pensieve.common.security.AuthenticationFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

//    DONE - Todo 좋아요 올리기
    //DONETodo 1 : 11.13 jamoutils 테스트 + res tes
    //TODO 금지어!!!!!!!!!!!!!!!!


    @PostMapping
    @Operation(summary = "게시물올리기(확정시)" )
    public PostDetailRes insertPost(@RequestBody PostInsDto dto, @RequestPart MultipartFile finImg){
        if(dto!=null){
//            return service.insPost(dto);
            return service.insPostTest(dto,finImg);
        }
        else{
            //값이 없을때 리턴
           throw new RuntimeException("값이 입력되지 않음");
        }
    }

    @GetMapping("/preset")
    @Operation(summary = "랜덤디자인" )
    public List<List<String>> getRandomDesign(@RequestBody PostInsDto dto){
        if(dto!=null){
//            return service.insPost(dto);
            return service.getRandomDesign(dto);
        }
        else{
            //값이 없을때 리턴
            throw new RuntimeException("값이 입력되지 않음");
        }
    }

    @PatchMapping("/like")
    public int postLikeBtn(@RequestParam Long postId){
        return service.postLikeBtn(postId);
    }

    @GetMapping
    public PostDetailRes getPostDetail(){
        return service.getPostDetail();
    }


    @PatchMapping("/report")
    public int postReport(@RequestParam Long postId){
        return service.postRepost(postId);
    }


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

}
