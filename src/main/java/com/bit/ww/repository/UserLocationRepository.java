package com.bit.ww.repository;


import com.bit.ww.entity.UserLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLocationRepository extends JpaRepository<UserLocationEntity, Integer> {

    List<UserLocationEntity> findByUserid(String keyword);
    int countByUserid(String userid);
}
