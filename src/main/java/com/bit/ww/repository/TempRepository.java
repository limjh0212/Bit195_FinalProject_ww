package com.bit.ww.repository;

import com.bit.ww.entity.TempEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TempRepository extends JpaRepository<TempEntity, Integer> {
    // 임시저장리스트 불러오기
    List<TempEntity> findAllByWriterOrderByNumDesc(String writer);
    // 글 개수
    int countAllByWriter(String writer);
}
