package com.bit.ww.repository;

import com.bit.ww.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
    BoardEntity findByBoardname(String boardname);
}
