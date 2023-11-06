package com.example.pensieve.board;

import com.example.pensieve.board.model.PostDetailRes;
import com.example.pensieve.board.model.PostInsDto;
import com.example.pensieve.common.entity.PostBoxEntity;
import com.example.pensieve.common.entity.UserEntity;
import com.example.pensieve.common.repository.PostBoxRepository;
import com.example.pensieve.common.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public PostDetailRes getPostDetail(){

        PostBoxEntity post = postRep.getReferenceById(getRandomPostId());
        post.setHits(post.getHits()+1);
        postRep.save(post);
//        List<Character> list = post.getCtnt();
        List<Character> list = new ArrayList<>();
        //Todo 초중종성 분해 후 리턴
        //Todo hits 올리기
        //Todo hits 올리면서 시간내(1분) 5회이상 발생시 막는 거 어떻게하지?

        LocalDate date = post.getCreatedAt().toLocalDate();
        return PostDetailRes.builder()
                .postId(post.getPostId())
                .charList(list)
                .likes(post.getLikes())
                .hits(post.getHits())
                .createdAt(date)
                .build();
    }

    private Long getRandomPostId(){
        List<Long> list = postRep.findAllPostId();
        log.info(list.toString());
        int randomNum = (int)(Math.random()*list.size());
        log.info("postId : {} ",list.get(randomNum));
        return list.get(randomNum);
        //Done 랜덤 postId리턴 .banYn N/
    }
}
