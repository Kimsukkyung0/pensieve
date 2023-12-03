package com.example.pensieve.admin;

import com.example.pensieve.common.entity.PostBoxEntity;
import com.example.pensieve.common.repository.PostBoxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {
    private final PostBoxRepository postRep;

    public int banPost(Long postId){
        PostBoxEntity pbEnti = postRep.getReferenceById(postId);
        try{
            pbEnti.setBanYn('Y');
            postRep.save(pbEnti);
            return 1;
        } catch(RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException("삭제처리 오류");
        }
    }

    public int banPostRev(Long postId){
        PostBoxEntity pbEnti = postRep.getReferenceById(postId);
        try{
            pbEnti.setBanYn('N');
            postRep.save(pbEnti);
            return 1;
        } catch(RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException("삭제처리 오류");
        }
    }


    //Todo paging작업/정렬작업
    public List<PostBoxEntity> getReportedPosts(){
        List<PostBoxEntity> list = postRep.findPostsByReportButNotBanned(1L);
        return list;
    }
}
