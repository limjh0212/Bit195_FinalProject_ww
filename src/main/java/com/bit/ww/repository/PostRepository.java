package com.bit.ww.repository;

import com.bit.ww.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    // 게시판 이름으로 게시물 목록 찾기
    List<PostEntity> findByBoardnameOrderByNumDesc(String boardname);
    int countAllByBoardname(String boardname);
    // 게시물 단건 찾기
    PostEntity findByNum(int num);
    // 각 게시판 별 제목 검색
    List<PostEntity> findByTitleIgnoreCaseIsContainingAndBoardnameOrderByNumDesc(String title, String boardname);
    int countAllByTitleIgnoreCaseIsContainingAndBoardname(String title, String boardname);
    // 각 게시판 별 제목+내용 검색
    List<PostEntity> findByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndBoardnameOrderByNumDesc(String title, String content, String boardname);
    int countAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndBoardname(String title, String content, String boardname);
    // 관리 - 작성자 검색 (Id, 닉네임)
    List<PostEntity> findByUidOrderByNumDesc(String uid);
    int countAllByUid(String uid);
    // 마이페이지 - 각 게시판 별 내가 작성한 글
    List<PostEntity> findByBoardnameAndUidOrderByNumDesc(String boardname, String uid);
    int countAllByBoardnameAndUid(String boardname, String uid);
    // 마이페이지 - 각 게시판 별 내가 작성한 글에서 제목 검색
    // 마이페이지 - 각 게시판 별 내가 작성한 글에서 제목 + 내용 검색
    // 인기글 찾기

}
