package com.example.pensieve;


import com.example.pensieve.board.model.PostInsDto;
import com.example.pensieve.common.entity.PostBoxEntity;
import com.example.pensieve.common.entity.UserEntity;
import com.example.pensieve.common.repository.PostBoxRepository;
import com.example.pensieve.common.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;


@Slf4j
@ExtendWith(SpringExtension.class)
public class PostBoxServiceTest {
    @MockBean
    PostBoxRepository postRep;

    @MockBean
    UserRepository userRep;

    @Test
    void insertTest(){
        //test Dto생성
        String testCtnt = "test";
        PostInsDto dto = PostInsDto.builder().userId(2L).ctnt(testCtnt).build();

        UserEntity enti = userRep.getReferenceById(2L);

        PostBoxEntity postEnti = PostBoxEntity.builder().userEntity(enti).ctnt(dto.getCtnt()).build();

        when(postRep.save(postEnti));
    }


    @Test
    void KoreanToUnicordTest(){
        String testWord = "테스트 니?";
        byte[] bytes = testWord.getBytes();
        log.info("{}",bytes);
//        String[][] resultList = new String[][]{};
//        for(byte b : bytes){
//            resultList.
//        }
//        log.info("{}");
    }
}
