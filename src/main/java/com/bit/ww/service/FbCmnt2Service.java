package com.bit.ww.service;

import com.bit.ww.dto.FbCmnt2DTO;
import com.bit.ww.entity.FbCmnt2Entity;
import com.bit.ww.repository.FbCmnt2Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FbCmnt2Service {
    private FbCmnt2Repository fbCmnt2Repository;

    @Transactional
    public List<FbCmnt2DTO> getFbCmnt2List(int cmntnum) {
        List<FbCmnt2Entity> fbCmnt2Entities = fbCmnt2Repository.findAllByCmntnumOrderByNumAsc(cmntnum);
        List<FbCmnt2DTO> fbCmnt2DTOList = new ArrayList<>();

        for (FbCmnt2Entity fbCmnt2Entity : fbCmnt2Entities) {
            FbCmnt2DTO fbCmnt2DTO = FbCmnt2DTO.builder()
                    .num(fbCmnt2Entity.getNum())
                    .cmntnum(fbCmnt2Entity.getCmntnum())
                    .writer(fbCmnt2Entity.getWriter())
                    .content(fbCmnt2Entity.getContent())
                    .regdate(fbCmnt2Entity.getRegdate())
                    .editdate(fbCmnt2Entity.getEditdate())
                    .build();

            fbCmnt2DTOList.add(fbCmnt2DTO);
        }
        return fbCmnt2DTOList;
    }

    @Transactional
    public int saveCmnt2(FbCmnt2DTO fbCmnt2DTO){
        return fbCmnt2Repository.save(fbCmnt2DTO.toEntity()).getNum();
    }

    @Transactional
    public void deleteCmnt2(int num){
        fbCmnt2Repository.deleteById(num);
    }

    @Transactional
    public boolean existCmnt2(int cmntnum) {
        return fbCmnt2Repository.existsByCmntnum(cmntnum);
    }

}
