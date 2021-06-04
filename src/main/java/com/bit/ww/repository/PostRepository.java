package com.bit.ww.repository;

import com.bit.ww.entity.BoardEntity;
import com.bit.ww.entity.MemberEntity;
import com.bit.ww.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    // 게시판 이름으로 게시물 목록 찾기
    List<PostEntity> findByBoardOrderByNumDesc(BoardEntity boardEntity);
    int countAllByBoard(BoardEntity boardEntity);
    // 게시물 단건 찾기
    PostEntity findByNum(int num);
    // 각 게시판 별 제목 검색
    List<PostEntity> findByTitleIgnoreCaseIsContainingAndBoardOrderByNumDesc(String title, BoardEntity boardEntity);
    int countAllByTitleIgnoreCaseIsContainingAndBoard(String title, BoardEntity boardEntity);
    // 각 게시판 별 제목+내용 검색
    List<PostEntity> findByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndBoardOrderByNumDesc(String title, String content, BoardEntity boardEntity);
    int countAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndBoard(String title, String content, BoardEntity boardEntity);
    // 관리 - 작성자 검색 (Id, 닉네임)
    List<PostEntity> findByMemberOrderByNumDesc(MemberEntity memberEntity);
    int countAllByMember(MemberEntity memberEntity);
    // 마이페이지 - 각 게시판 별 내가 작성한 글
    List<PostEntity> findByBoardAndMemberOrderByNumDesc(BoardEntity boardEntity, MemberEntity memberEntity);
    int countAllByBoardAndMember(BoardEntity boardEntity, MemberEntity memberEntity);
    // 마이페이지 - 각 게시판 별 내가 작성한 글에서 제목 검색
    // 마이페이지 - 각 게시판 별 내가 작성한 글에서 제목 + 내용 검색
    // 인기글 찾기

}
