package com.bit.ww.repository;

import com.bit.ww.entity.CmntEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CmntRepository extends JpaRepository<CmntEntity, Integer> {
    // 댓글
    List<CmntEntity> findAllByBoardnumAndPostnumAndDepthOrderByNumAsc(int boardnum, int postnum, int depth);
    // 대댓글 존재 여부
    boolean existsByCmntnum(int cmntnum);
    // 대댓글
    List<CmntEntity> findAllByBoardnumAndPostnumAndCmntnumAndDepthOrderByNumAsc(int boardnum, int postnum, int cmntnum, int depth);
    // 내가쓴댓글
    List<CmntEntity> findAllByWriterOrderByNumDesc(String writer);
}
