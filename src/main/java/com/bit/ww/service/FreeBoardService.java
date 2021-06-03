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
    // CRUD 메인 기능
    @Transactional
    public List<FreeBoardDTO> getFreeBoardList() {
        List<FreeBoardEntity> freeBoardEntities = freeBoardRepository.findAllByOrderByNumDesc();
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
                .build();

        return freeBoardDTO;
    }

    @Transactional
    public FreeBoardEntity savePost(FreeBoardDTO freeBoardDTO){
        return freeBoardRepository.save(freeBoardDTO.toEntity());
    }

    @Transactional
    public void deletePost(int num){
        freeBoardRepository.deleteById(num);
    }

    @Transactional
    public int countList(){
        return freeBoardRepository.countAllByNumIsNotNull();
    }

    //검색 기능
    @Transactional
    public List<FreeBoardDTO> getListByTitle(String title) {
        List<FreeBoardEntity> tempEntities = freeBoardRepository.findAllByTitleIgnoreCaseIsContainingOrderByNumDesc(title);
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
                    .build();

            tempDTOList.add(tempDTO);
        }
        return tempDTOList;
    }

    @Transactional
    public int countListByTitle(String title){
        return freeBoardRepository.countAllByTitleIgnoreCaseIsContaining(title);
    }

    @Transactional
    public List<FreeBoardDTO> getListByTitleAndContent(String title, String content) {
        List<FreeBoardEntity> tempEntities = freeBoardRepository.findAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingOrderByNumDesc(title, content);
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
                    .build();

            tempDTOList.add(tempDTO);
        }
        return tempDTOList;
    }
    @Transactional
    public int countListByTitleAndContent(String title, String content){
        return freeBoardRepository.countAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContaining(title, content);
    }

    //관리 기능
    @Transactional
    public List<FreeBoardDTO> getListByWriter(String writer) {
        List<FreeBoardEntity> tempEntities = freeBoardRepository.findAllByWriterOrderByNumDesc(writer);
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
                    .build();

            tempDTOList.add(tempDTO);
        }
        return tempDTOList;
    }

    @Transactional
    public int countListByWriter(String writer){
        return freeBoardRepository.countAllByWriter(writer);
    }

    @Transactional
    public List<FreeBoardDTO> getListByWriterAndTitle(String title, String writer) {
        List<FreeBoardEntity> tempEntities = freeBoardRepository.findAllByTitleIgnoreCaseIsContainingAndWriterOrderByNumDesc(title, writer);
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
                    .build();

            tempDTOList.add(tempDTO);
        }
        return tempDTOList;
    }

    @Transactional
    public int countListByWriterAndTitle(String title, String writer){
        return freeBoardRepository.countAllByTitleIgnoreCaseIsContainingAndWriter(title, writer);
    }

    @Transactional
    public List<FreeBoardDTO> getListByWriterAndTitleAndContent(String title, String content, String writer) {
        List<FreeBoardEntity> tempEntities = freeBoardRepository.findAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndWriterOrderByNumDesc(title, content, writer);
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
                    .build();

            tempDTOList.add(tempDTO);
        }
        return tempDTOList;
    }

    @Transactional
    public int countListByWriterAndTitleAndContent(String title, String content, String writer){
        return freeBoardRepository.countAllByTitleIgnoreCaseIsContainingOrContentIgnoreCaseIsContainingAndWriter(title, content, writer);
    }
}
