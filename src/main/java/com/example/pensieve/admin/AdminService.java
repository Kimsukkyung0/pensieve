package com.example.pensieve.admin;

import com.example.pensieve.common.entity.PostBoxEntity;
import com.example.pensieve.common.repository.PostBoxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private PostBoxRepository postRep;

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