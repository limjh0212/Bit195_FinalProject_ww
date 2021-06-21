package com.bit.ww.repository;

import com.bit.ww.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepository extends JpaRepository<ImgEntity, Integer> {
    ImgEntity save(ImgEntity imgEntity);
    List<ImgEntity> findAllByPostid(int postid);
}
