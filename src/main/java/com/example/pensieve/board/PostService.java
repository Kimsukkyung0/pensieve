package com.example.pensieve.board;

import com.example.pensieve.board.model.PostInsDto;
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
public class PostService {
    private final PostBoxRepository postRep;
    private final UserRepository usrRep;
    public int insPost(PostInsDto dto){
        UserEntity userEntity = usrRep.getReferenceById(dto.getUserId());
        PostBoxEntity entity = PostBoxEntity.builder().userEntity(userEntity).ctnt(dto.getCtnt()).build();
        postRep.save(entity);
        return entity.getPostId().intValue();
    }

    public int delPost(Long postId){
        PostBoxEntity pbEnti = postRep.getReferenceById(postId);
        pbEnti.setBanYn('Y');
        try{
            postRep.save(pbEnti);
            return 1;
        } catch(RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException("삭제처리 오류");
        }
    }
}
