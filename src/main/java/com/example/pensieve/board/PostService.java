package com.example.pensieve.board;

import com.example.pensieve.board.model.PostDetailRes;
import com.example.pensieve.board.model.PostInsDto;
import com.example.pensieve.common.entity.LikeEntity;
import com.example.pensieve.common.entity.PostBoxEntity;
import com.example.pensieve.common.entity.UserEntity;
import com.example.pensieve.common.repository.LikesRepository;
import com.example.pensieve.common.repository.PostBoxRepository;
import com.example.pensieve.common.repository.UserRepository;
import com.example.pensieve.common.utils.SplitUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class PostService {
    private final PostBoxRepository postRep;
    private final UserRepository usrRep;
    private final LikesRepository likeRep;



    public PostDetailRes insPostTest(PostInsDto dto, MultipartFile finImg){
//    //test1
        String rdNm = UUID.randomUUID().toString();
        log.info(rdNm);



        UserEntity userEntity = usrRep.getReferenceById(dto.getUserId());

        PostBoxEntity entity = PostBoxEntity.builder()
                .userEntity(userEntity)
                .ctnt(dto.getCtnt())
                .img(finImg.getName())
                .build();
        postRep.save(entity);

        return PostDetailRes.builder()
                .postId(entity.getPostId())
                .img(finImg.getOriginalFilename())
                .createdAt(entity.getCreatedAt().toLocalDate())
                .build();

//        List<List<String>> ctnt = SplitUtils.split(dto.getCtnt());

    }

    public List<List<String>> getRandomDesign(PostInsDto dto){
        List<List<String>> ctnt = SplitUtils.split(dto.getCtnt());
        //여기서 , 유니코드로 변환해서 repository로 통신해서 디자인을 반환하면됨
        //근데 db구조 어떻게바꾸죸ㅋㅋ
        return ctnt;
    }


    //랜덤보기
    public PostDetailRes getPostDetail(){

        PostBoxEntity post = postRep.getReferenceById(getRandomPostId());
        post.setHits(post.getHits()+1);
        postRep.save(post);
//        List<Character> list = post.getCtnt();
        List<Character> list = new ArrayList<>();
        //Todo hits 올리면서 시간내(1분) 5회이상 발생시 막는 거 어떻게하지?-프로느에서
        //https://needjarvis.tistory.com/644 여기참고/~

        //DONE hits 올리기

        LocalDate date = post.getCreatedAt().toLocalDate();
        return PostDetailRes.builder()
                .postId(post.getPostId())
//                .charList(list)
                .likes(post.getLikes())
                .hits(post.getHits())
                .createdAt(date)
                .build();
    }

    //존재하는 포스트 중 id 하나를 랜덤으로 생성
    private Long getRandomPostId(){
        List<Long> list = postRep.findAllPostId();
        log.info(list.toString());
        int randomNum = (int)(Math.random()*list.size());
        log.info("postId : {} ",list.get(randomNum));
        return list.get(randomNum);
        //Done 랜덤 postId리턴 .banYn N/
    }

    //좋아요메서드
    public int postLikeBtn(Long postId){

        Optional<LikeEntity> like = likeRep.likesYn(postId, 1L);

        try {

            if (like.isPresent()) {
                likeRep.delete(like.get());

            } else if (like.isEmpty()) {
                //존재하지않는다면?
                LikeEntity newLikeEnti = LikeEntity.builder()
                        .userEntity(usrRep.getReferenceById(1L))
                        .postBoxEntity(postRep.getReferenceById(postId))
                        .build();
                likeRep.save(newLikeEnti);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(like.get().getId()!=null ){
            return 1;
        }
        return 0;
    }



//    public int insPost(PostInsDto dto){
////        UserEntity userEntity = usrRep.getReferenceById(dto.getUserId());
//        //1번 유저로 고정해서 ajax test
//        UserEntity userEntity = usrRep.getReferenceById(1L);
//        PostBoxEntity entity = PostBoxEntity.builder().userEntity(userEntity).ctnt(dto.getCtnt()).build();
//        postRep.save(entity);
//        return entity.getPostId().intValue();
//    }


}
