package com.bit.ww.repository;

import com.bit.ww.entity.FbCmntEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FbCmntRepository extends JpaRepository<FbCmntEntity, Integer> {
    List<FbCmntEntity> findAllByBoardnumOrderByNumAsc(int boardnum);
}
