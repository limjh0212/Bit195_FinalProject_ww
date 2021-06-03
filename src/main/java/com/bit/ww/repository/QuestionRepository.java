package com.bit.ww.repository;

import com.bit.ww.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    // 메인 기능
    List<QuestionEntity> findAllByOrderByNumDesc();
    int countAllByNumIsNotNull();
    // 최근 작성된 문의 목록
    List<QuestionEntity> findAllByRegdateIsBetweenOrderByNumDesc(LocalDateTime startdate, LocalDateTime enddate);
    int countAllByRegdateIsBetween(LocalDateTime startdate, LocalDateTime enddate);
    // 미답변 문의 목록
    List<QuestionEntity> findAllByIsansweredIsFalseOrderByNumDesc();
    int countAllByIsansweredIsFalse();
    // 검색 기능 - 작성자
    List<QuestionEntity> findAllByWriterOrderByNumDesc(String writer);
    int countAllByWriter(String writer);
}
