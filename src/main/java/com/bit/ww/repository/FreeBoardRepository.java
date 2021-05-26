package com.bit.ww.repository;

import com.bit.ww.entity.FreeBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoardEntity, Integer> {
    // 메인 기능
    List<FreeBoardEntity> findAllByIstempIsFalseOrderByNumDesc();
    List<FreeBoardEntity> findAllByWriterAndIstempIsTrueOrderByNumDesc(String writer);
    // 글 개수
    int countAllByIstempIsFalse();
    int countAllByWriterAndIstempIsTrue(String writer);
    // 검색 기능 Todo: Service, Controller 만들고 테스트 필요.
    List<FreeBoardEntity> findAllByIstempIsFalseAndTitleIsContainingIgnoreCaseOrderByNumDesc(String title);
    List<FreeBoardEntity> findAllByTitleOrContentContainingIgnoreCaseOrderByNumDesc(String title, String content);
    // 관리 기능 Todo: Service, Controller 만들고 테스트 필요.
    List<FreeBoardEntity> findAllByIstempIsFalseAndWriterIsContainingOrderByNumDesc(String writer);
    int countAllByWriterAndIstempIsFalse(String writer);
    List<FreeBoardEntity> findAllByIstempIsFalseAndWriterIsContainingAndTitleOrContentIsContainingOrderByNumDesc(String writer, String title, String content);

}
