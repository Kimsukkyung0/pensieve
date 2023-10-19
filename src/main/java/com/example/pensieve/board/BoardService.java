package com.example.pensieve.board;

import com.example.pensieve.board.model.BoardInsDto;
import com.example.pensieve.common.entity.PostBoxEntity;
import com.example.pensieve.common.entity.UserEntity;
import com.example.pensieve.common.repository.PostBoxRepository;
import com.example.pensieve.common.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BoardService {
    private final PostBoxRepository postRep;
    private final UserRepository usrRep;
    public int insPost(BoardInsDto dto){
        UserEntity userEntity = usrRep.findById(dto.getUserId()).get();
        PostBoxEntity entity = PostBoxEntity.builder().userEntity(userEntity).ctnt(dto.getCtnt()).build();
        postRep.save(entity);
        return entity.getPostId().intValue();
    }
}
