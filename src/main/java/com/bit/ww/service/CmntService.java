package com.bit.ww.service;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.entity.CmntEntity;
import com.bit.ww.repository.CmntRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CmntService {
    private CmntRepository cmntRepository;
    @Transactional
    public List<CmntDTO> getCmntList(int boardnum, int postnum, int depth) {
        List<CmntEntity> cmntEntities = cmntRepository.findAllByBoardnumAndPostnumAndDepthOrderByNumAsc(boardnum,postnum,depth);
        List<CmntDTO> cmntDTOList = new ArrayList<>();

        for (CmntEntity cmntEntity : cmntEntities) {
            CmntDTO cmntDTO = CmntDTO.builder()
                    .num(cmntEntity.getNum())
                    .boardnum(cmntEntity.getBoardnum())
                    .postnum(cmntEntity.getPostnum())
                    .cmntnum(cmntEntity.getCmntnum())
                    .depth(cmntEntity.getDepth())
                    .writer(cmntEntity.getWriter())
                    .content(cmntEntity.getContent())
                    .regdate(cmntEntity.getRegdate())
                    .editdate(cmntEntity.getEditdate())
                    .build();

            cmntDTOList.add(cmntDTO);
        }
        return cmntDTOList;
    }

    @Transactional
    public List<CmntDTO> getCmnt2List(int boardnum, int postnum, int cmntnum, int depth) {
        List<CmntEntity> cmnt2Entities = cmntRepository.findAllByBoardnumAndPostnumAndCmntnumAndDepthOrderByNumAsc(boardnum,postnum,cmntnum,depth);
        List<CmntDTO> cmnt2DTOList = new ArrayList<>();

        for (CmntEntity cmnt2Entity : cmnt2Entities) {
            CmntDTO cmnt2DTO = CmntDTO.builder()
                    .num(cmnt2Entity.getNum())
                    .boardnum(cmnt2Entity.getBoardnum())
                    .postnum(cmnt2Entity.getPostnum())
                    .cmntnum(cmnt2Entity.getCmntnum())
                    .depth(cmnt2Entity.getDepth())
                    .writer(cmnt2Entity.getWriter())
                    .content(cmnt2Entity.getContent())
                    .regdate(cmnt2Entity.getRegdate())
                    .editdate(cmnt2Entity.getEditdate())
                    .build();

            cmnt2DTOList.add(cmnt2DTO);
        }
        return cmnt2DTOList;
    }
    @Transactional
    public int saveCmnt(CmntDTO cmntDTO){
        return cmntRepository.save(cmntDTO.toEntity()).getNum();
    }

    @Transactional
    public void deleteCmnt(int num){
        cmntRepository.deleteById(num);
    }

    @Transactional
    public boolean existCmnt2(int cmntnum){
        return cmntRepository.existsByCmntnum(cmntnum);
    }
    @Transactional
    public List<CmntDTO> getMyCmntList(String writer) {
        List<CmntEntity> cmntEntities = cmntRepository.findAllByWriterOrderByNumDesc(writer);
        List<CmntDTO> cmntDTOList = new ArrayList<>();

        for (CmntEntity cmntEntity : cmntEntities) {
            CmntDTO cmntDTO = CmntDTO.builder()
                    .num(cmntEntity.getNum())
                    .boardnum(cmntEntity.getBoardnum())
                    .postnum(cmntEntity.getPostnum())
                    .cmntnum(cmntEntity.getCmntnum())
                    .depth(cmntEntity.getDepth())
                    .writer(cmntEntity.getWriter())
                    .content(cmntEntity.getContent())
                    .regdate(cmntEntity.getRegdate())
                    .editdate(cmntEntity.getEditdate())
                    .build();

            cmntDTOList.add(cmntDTO);
        }
        return cmntDTOList;
    }
}
