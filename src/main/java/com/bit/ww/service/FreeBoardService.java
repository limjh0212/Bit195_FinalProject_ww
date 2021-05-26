package com.bit.ww.service;

import com.bit.ww.dto.FreeBoardDTO;
import com.bit.ww.entity.FreeBoardEntity;
import com.bit.ww.repository.FreeBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FreeBoardService {
    private FreeBoardRepository freeBoardRepository;

    @Transactional
    public List<FreeBoardDTO> getFreeBoardList() {
        List<FreeBoardEntity> freeBoardEntities = freeBoardRepository.findAllByIstempIsFalseOrderByNumDesc();
        List<FreeBoardDTO> freeBoardDTOList = new ArrayList<>();

        for (FreeBoardEntity freeBoardEntity : freeBoardEntities) {
            FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder()
                    .num(freeBoardEntity.getNum())
                    .writer(freeBoardEntity.getWriter())
                    .title(freeBoardEntity.getTitle())
                    .content(freeBoardEntity.getContent())
                    .regdate(freeBoardEntity.getRegdate())
                    .editdate(freeBoardEntity.getEditdate())
                    .readcount(freeBoardEntity.getReadcount())
                    .istemp(freeBoardEntity.isIstemp())
                    .build();

            freeBoardDTOList.add(freeBoardDTO);
        }
        return freeBoardDTOList;
    }

    @Transactional
    public FreeBoardDTO getPost(int num) {
        Optional<FreeBoardEntity> freeBoardEntityWrapper = freeBoardRepository.findById(num);
        FreeBoardEntity freeBoardEntity = freeBoardEntityWrapper.get();

        FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder()
                .num(freeBoardEntity.getNum())
                .writer(freeBoardEntity.getWriter())
                .title(freeBoardEntity.getTitle())
                .content(freeBoardEntity.getContent())
                .regdate(freeBoardEntity.getRegdate())
                .editdate(freeBoardEntity.getEditdate())
                .readcount(freeBoardEntity.getReadcount())
                .istemp(freeBoardEntity.isIstemp())
                .build();

        return freeBoardDTO;
    }

    @Transactional
    public int savePost(FreeBoardDTO freeBoardDTO){
        return freeBoardRepository.save(freeBoardDTO.toEntity()).getNum();
    }

    @Transactional
    public void deletePost(int num){
        freeBoardRepository.deleteById(num);
    }

    @Transactional
    public List<FreeBoardDTO> getTempList(String writer) {
        List<FreeBoardEntity> tempEntities = freeBoardRepository.findAllByWriterAndIstempIsTrueOrderByNumDesc(writer);
        List<FreeBoardDTO> tempDTOList = new ArrayList<>();

        for (FreeBoardEntity tempEntity : tempEntities) {
            FreeBoardDTO tempDTO = FreeBoardDTO.builder()
                    .num(tempEntity.getNum())
                    .writer(tempEntity.getWriter())
                    .title(tempEntity.getTitle())
                    .content(tempEntity.getContent())
                    .regdate(tempEntity.getRegdate())
                    .editdate(tempEntity.getEditdate())
                    .readcount(tempEntity.getReadcount())
                    .istemp(tempEntity.isIstemp())
                    .build();

            tempDTOList.add(tempDTO);
        }
        return tempDTOList;
    }

    @Transactional
    public int countList(){
        return freeBoardRepository.countAllByIstempIsFalse();
    }

    @Transactional
    public int countListByTemp(String writer){
        return freeBoardRepository.countAllByWriterAndIstempIsTrue(writer);
    }

    @Transactional
    public int countListByWriter(String writer){
        return freeBoardRepository.countAllByWriterAndIstempIsFalse(writer);
    }

}
