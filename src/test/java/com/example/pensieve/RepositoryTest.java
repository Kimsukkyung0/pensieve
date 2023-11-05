package com.example.pensieve;

import com.example.pensieve.common.entity.UserEntity;
import com.example.pensieve.common.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class RepositoryTest {
    @Autowired
    UserRepository usrRep;

    @Test
    void UserEntityTest(){
        String testEmail = "smk93021@naver.com";
        Optional<UserEntity> testEntity = usrRep.findByEmail(testEmail);
        if(testEntity.isEmpty()){
            log.info("failed");
        }
        log.info("{} 유저 entity 생성 성공",testEntity.get().getNickNm());
    }
}
