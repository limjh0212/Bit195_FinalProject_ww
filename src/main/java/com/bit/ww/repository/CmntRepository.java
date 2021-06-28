package com.bit.ww.repository;

import com.bit.ww.entity.CmntEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CmntRepository extends JpaRepository<CmntEntity, Integer> {
    // 댓글
    List<CmntEntity> findAllByBoardnumAndPostnumAndDepthOrderByNumAsc(int boardnum, int postnum, int depth);
    // 대댓글 존재 여부
    boolean existsByCmntnum(int cmntnum);
    // 대댓글
    List<CmntEntity> findAllByBoardnumAndPostnumAndCmntnumAndDepthOrderByNumAsc(int boardnum, int postnum, int cmntnum, int depth);
    // 내가쓴댓글
    Page<CmntEntity> findAllByWriterOrderByNumDesc(String writer, Pageable pageable);
    int countAllByWriter(String writer);
    // QnA게시판 답변 찾기
    Optional<CmntEntity> findByBoardnumAndPostnum(int boardnum, int postnum);
}
