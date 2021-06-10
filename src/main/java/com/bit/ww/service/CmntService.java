package com.bit.ww.service;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.entity.CmntEntity;
import com.bit.ww.repository.CmntRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CmntService {
    private final CmntRepository cmntRepository;

    public CmntDTO convertEntityToDTO(CmntEntity cmntEntity){
        return CmntDTO.builder()
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
    }
    @Transactional
    public List<CmntDTO> getCmntList(int boardnum, int postnum, int depth) {
        List<CmntEntity> cmntEntities = cmntRepository.findAllByBoardnumAndPostnumAndDepthOrderByNumAsc(boardnum,postnum,depth);
        List<CmntDTO> cmntDTOList = new ArrayList<>();

        for (CmntEntity cmntEntity : cmntEntities) {
            cmntDTOList.add(this.convertEntityToDTO(cmntEntity));
        }
        return cmntDTOList;
    }

    @Transactional
    public List<CmntDTO> getCmnt2List(int boardnum, int postnum, int cmntnum, int depth) {
        List<CmntEntity> cmntEntities = cmntRepository.findAllByBoardnumAndPostnumAndCmntnumAndDepthOrderByNumAsc(boardnum,postnum,cmntnum,depth);
        List<CmntDTO> cmntDTOList = new ArrayList<>();

        for (CmntEntity cmntEntity : cmntEntities) {
            cmntDTOList.add(this.convertEntityToDTO(cmntEntity));
        }
        return cmntDTOList;
    }
    @Transactional
    public CmntEntity saveCmnt(CmntDTO cmntDTO){
        return cmntRepository.save(cmntDTO.toEntity());
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
            cmntDTOList.add(this.convertEntityToDTO(cmntEntity));
        }
        return cmntDTOList;
    }

    public CmntDTO getAnswer(int boardnum, int postnum) {
        Optional<CmntEntity> cmntEntityWrapper = cmntRepository.findByBoardnumAndPostnum(boardnum, postnum);
        CmntEntity cmntEntity = cmntEntityWrapper.get();

        return convertEntityToDTO(cmntEntity);
    }
}
