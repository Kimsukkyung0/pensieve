package com.example.pensieve.board;

import com.example.pensieve.board.model.BoardInsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/test")
public class BoardController {

    private final BoardService service;
    @PostMapping
    public int insertPost(@RequestBody BoardInsDto dto){
        if(dto!=null){
            return service.insPost(dto);
        }
        else{
            return 0;
        }


    }

}
