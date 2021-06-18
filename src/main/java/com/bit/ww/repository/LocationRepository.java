package com.bit.ww.repository;

import com.bit.ww.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, String> {
    List<LocationEntity> findByAddress2Containing(String keyword);
}
