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
    private LikeRepository likeRepository;
    // 좋아요 조건 - 한 사람당 한 게시물에 한 번만 좋아요를 누를 수 있음.
    // 다시 누르면 취소
    // 또다시 누르면 좋아요
    // 즉각적으로 눈에 보여주기 ( 뷰 단 - 짝수면 누르지 않은것,홀수면 누른 것으로 보고 최종적으로 페이지가 이동될때 값을 저장)

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
