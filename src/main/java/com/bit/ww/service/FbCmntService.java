package com.bit.ww.service;

import com.bit.ww.dto.FbCmntDTO;
import com.bit.ww.entity.FbCmntEntity;
import com.bit.ww.repository.FbCmntRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FbCmntService {
    private FbCmntRepository fbCmntRepository;

    @Transactional
    public List<FbCmntDTO> getFbCmntList(int boardnum) {
        List<FbCmntEntity> fbCmntEntities = fbCmntRepository.findAllByBoardnumOrderByNumAsc(boardnum);
        List<FbCmntDTO> fbCmntDTOList = new ArrayList<>();

        for (FbCmntEntity fbCmntEntity : fbCmntEntities) {
            FbCmntDTO fbCmntDTO = FbCmntDTO.builder()
                    .num(fbCmntEntity.getNum())
                    .boardnum(fbCmntEntity.getBoardnum())
                    .writer(fbCmntEntity.getWriter())
                    .content(fbCmntEntity.getContent())
                    .regdate(fbCmntEntity.getRegdate())
                    .editdate(fbCmntEntity.getEditdate())
                    .build();

            fbCmntDTOList.add(fbCmntDTO);
        }
        return fbCmntDTOList;
    }

    @Transactional
    public int saveCmnt(FbCmntDTO fbCmntDTO){
        return fbCmntRepository.save(fbCmntDTO.toEntity()).getNum();
    }

    @Transactional
    public void deleteCmnt(int num){
        fbCmntRepository.deleteById(num);
    }

}
