package com.bit.ww.repository;

import com.bit.ww.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepository extends JpaRepository<ImgEntity, Integer> {
    ImgEntity save(ImgEntity imgEntity);

    List<ImgEntity> findAllByPostid(int postid);

    // Exist
    Boolean existsAllByBoardidAndPostid(int boardid, int postid);

    //Postid List 출력용
    List<ImgEntity> findAllByBoardidOrderByNumDesc(int boardid);

    //Boardid & Postid로 List출력
    List<ImgEntity> findAllByBoardidAndPostidOrderByNumDesc(int boardid, int postid);

}
