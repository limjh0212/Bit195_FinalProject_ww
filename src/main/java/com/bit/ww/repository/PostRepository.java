package com.bit.ww.repository;

import com.bit.ww.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    // 게시판 이름으로 게시물 목록 찾기
    Page<PostEntity> findAllByBoardname(String boardname, Pageable pageable);
    int countAllByBoardname(String boardname);
    // 각 게시판 별 제목 검색
    List<PostEntity> findByTitleIgnoreCaseIsContainingAndBoardnameOrderByNumDesc(String title, String boardname);
    int countAllByTitleIgnoreCaseIsContainingAndBoardname(String title, String boardname);
    // 각 게시판 별 제목+내용 검색
    List<PostEntity> findByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndBoardnameOrderByNumDesc(String title, String content, String boardname);
    int countAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndBoardname(String title, String content, String boardname);
    // 관리 - 작성자 검색 (Id, 닉네임)
    List<PostEntity> findByUidOrderByNumDesc(String uid);
    int countAllByUid(String uid);
    List<PostEntity> findByWriterOrderByNumDesc(String writer);
    int countAllByWriter(String writer);
    // 관리 - 총 게시물 수
    int countAllByBoardnumIsNot(int boardnum);
    // 관리 - 최근 게시물 수
    int countAllByRegdateIsBetweenAndBoardnumIsNot(LocalDateTime start, LocalDateTime end, int boardnum);
    int countAllByRegdateIsBetweenAndBoardnum(LocalDateTime start, LocalDateTime end, int boardnum);
    // 관리 - 미해결 문의 수
    List<PostEntity> findByBoardnumAndIsansweredIsFalseOrderByNumDesc(int boardnum);
    int countAllByBoardnumAndIsansweredIsFalse(int boadrnum);
    // 마이페이지 - 각 게시판 별 내가 작성한 글
    List<PostEntity> findByBoardnameAndUidOrderByNumDesc(String boardname, String uid);
    int countAllByBoardnameAndUid(String boardname, String uid);
    // 마이페이지 - 각 게시판 별 내가 작성한 글에서 제목 검색
    List<PostEntity> findByBoardnameAndUidAndTitleIgnoreCaseOrderByNumDesc(String boardname, String uid, String title);
    int countAllByBoardnameAndUidAndTitleIgnoreCase(String boardname, String uid, String title);
    // 마이페이지 - 각 게시판 별 내가 작성한 글에서 제목 + 내용 검색
    List<PostEntity> findByBoardnameAndUidAndTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingOrderByNumDesc(String boardname, String uid, String title, String content);
    int countAllByBoardnameAndUidAndTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContaining(String boardname, String uid, String title, String content);
    // 인기글 찾기
    List<PostEntity> findByBoardnameOrderByReadcountDesc(String boardname);
    List<PostEntity> findByBoardnameOrderByLikecountDesc(String boardname);
}
