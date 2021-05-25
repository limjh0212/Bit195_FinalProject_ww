package com.bit.ww.repository;

import com.bit.ww.entity.FbCmnt2Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FbCmnt2Repository extends JpaRepository<FbCmnt2Entity, Integer> {
    List<FbCmnt2Entity> findAllByCmntnumOrderByNumAsc(int cmntnum);
    boolean existsByCmntnum(int cmntnum);
}
