package com.example.pensieve.board;

import com.example.pensieve.board.model.PostDetailRes;
import com.example.pensieve.board.model.PostInsDto;
import com.example.pensieve.common.security.AuthenticationFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "게시물올리기(확정시)" )
    public PostDetailRes insertPost(@RequestPart PostInsDto dto, @RequestPart(required = false) MultipartFile finImg){
        if(dto!=null){
//            return service.insPost(dto);
            return service.insPostTest(dto,finImg);
            //1.유저아이디/내용 2.최종이미지
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
    @Operation(summary = "좋아요기능" )
    public int postLikeBtn(@RequestParam Long postId){
        return service.postLikeBtn(postId);
    }

    @GetMapping
    @Operation(summary = "상세보기" )
    public PostDetailRes getPostDetail(){
        return service.getPostDetail();
    }


    //TODO 차단한사람 목록저장하는 테이블(ENtity( 생성
    @PatchMapping("/report")
    @Operation(summary = "신고하기" )
    public int postReport(@RequestParam Long postId){
        return service.postReport(postId);

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
