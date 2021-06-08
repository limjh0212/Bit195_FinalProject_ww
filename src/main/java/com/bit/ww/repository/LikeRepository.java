package com.bit.ww.repository;

import com.bit.ww.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    // 생성할 지에 대한 여부 확인을 위해
    boolean existsByBoardnumAndPostnumAndUid(int boardnum, int postnum, String uid);
    // 좋아요 취소
    Optional<LikeEntity> findByBoardnumAndPostnumAndUid(int boardnum, int postnum, String uid);
    // 좋아요한 리스트 출력
    List<LikeEntity> findByBoardnumAndUidOrderByNumDesc(int boardnum, String uid);
}
