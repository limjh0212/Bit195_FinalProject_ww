package com.bit.ww.repository;

import com.bit.ww.entity.FreeBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// Todo: 수정 미완 - 지울거 지우고, 확인할거 확인 !
public interface FreeBoardRepository extends JpaRepository<FreeBoardEntity, Integer> {
    // 메인 기능
    List<FreeBoardEntity> findAllByOrderByNumDesc();
    // 커뮤니티 메인화면 Todo: 인기글 출력필요.
    // 오류 : List<FreeBoardEntity> findAllByReadcountDesc();
    // 글 개수 Todo: 다른 방법 찾아보기.
    int countAllByNumIsNotNull();
    // 검색 기능
    List<FreeBoardEntity> findAllByTitleIgnoreCaseIsContainingOrderByNumDesc(String title);
    int countAllByTitleIgnoreCaseIsContaining(String title);
    List<FreeBoardEntity> findAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingOrderByNumDesc(String title, String content);
    int countAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContaining(String title, String content);
    // 관리 기능
    List<FreeBoardEntity> findAllByWriterOrderByNumDesc(String writer);
    int countAllByWriter(String writer);
    List<FreeBoardEntity> findAllByTitleIgnoreCaseIsContainingAndWriterOrderByNumDesc(String title, String writer);
    int countAllByTitleIgnoreCaseIsContainingAndWriter(String title, String writer);
    List<FreeBoardEntity> findAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndWriterOrderByNumDesc(String title, String content, String writer);
    int countAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndWriter(String title, String content, String writer);
}
