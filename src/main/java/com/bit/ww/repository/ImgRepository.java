package com.bit.ww.repository;

import com.bit.ww.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepository extends JpaRepository<ImgEntity, Integer> {
    ImgEntity save(ImgEntity imgEntity);
    List<ImgEntity> findAllByPostid(int postid);
    // Todo: 진행중 - column 추가 (img순서)
    int countAllByPostid(int postid);
    List<ImgEntity> findAllByBoardidOrderByNumDesc(int boardid);
}
