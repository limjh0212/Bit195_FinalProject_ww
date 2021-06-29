package com.bit.ww.service;

import com.bit.ww.dto.LikeDTO;
import com.bit.ww.entity.LikeEntity;
import com.bit.ww.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    // 게시물 마다의 좋아요 수
    @Transactional
    public boolean existLike(int boardnum, int postnum, String uid){
        return likeRepository.existsByBoardnumAndPostnumAndUid(boardnum, postnum, uid);
    }
    @Transactional
    public int saveLike(LikeDTO likeDTO){
        return likeRepository.save(likeDTO.toEntity()).getNum();
    }

    @Transactional
    public void deleteLike(int num){
        likeRepository.deleteById(num);
    }

    @Transactional
    public int findLikeNum(int boardnum, int postnum, String userid){
        Optional<LikeEntity> likeEntityWrapper = likeRepository.findByBoardnumAndPostnumAndUid(boardnum, postnum, userid);
        LikeEntity likeEntity = likeEntityWrapper.get();

        LikeDTO likeDTO = LikeDTO.builder()
                .num(likeEntity.getNum())
                .boardnum(likeEntity.getBoardnum())
                .postnum(likeEntity.getPostnum())
                .uid(likeEntity.getUid())
                .build();

        return likeDTO.getNum();
    }

}
