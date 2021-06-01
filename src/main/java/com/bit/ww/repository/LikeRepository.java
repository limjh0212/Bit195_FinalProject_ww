package com.bit.ww.repository;

import com.bit.ww.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    // 생성할 지에 대한 여부 확인을 위해
    boolean existsByBoardnumAndPostnumAndUserid(int boardnum, int postnum, String userid);
    // 좋아요 수
    int countAllByBoardnumAndPostnum(int boardnum, int postnum);
    // 좋아요 취소
    Optional<LikeEntity> findByBoardnumAndPostnumAndUserid(int boardnum, int postnum, String userid);

}
