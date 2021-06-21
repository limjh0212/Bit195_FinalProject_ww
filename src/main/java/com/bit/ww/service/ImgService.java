package com.bit.ww.service;

import com.bit.ww.dto.ImgDTO;
import com.bit.ww.dto.PostDTO;
import com.bit.ww.entity.ImgEntity;
import com.bit.ww.entity.PostEntity;
import com.bit.ww.repository.BoardRepository;
import com.bit.ww.repository.ImgRepository;
import com.bit.ww.repository.MemberRepository;
import com.bit.ww.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImgService {
    private final ImgRepository imgRepository;
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final FileHandler fileHandler;

    public ImgDTO convertEntityToDTO(ImgEntity imgEntity) {
    return ImgDTO.builder()
            .num(imgEntity.getNum())
            .postid(imgEntity.getPostid())
            .filename(imgEntity.getFilename())
            .originalname(imgEntity.getOriginalname())
            .storedpath(imgEntity.getStoredpath())
            .filesize(imgEntity.getFilesize())
            .build();
    }
    // Todo: OOTD 이미지 전체 출력
    // 하고자 하는 방식 : 각 게시물에 추가된 전체 이미지 출력
    @Transactional
    public List<ImgDTO> findImgs(int postid) {
        List<ImgEntity> imgEntities = imgRepository.findAllByPostid(postid);
        List<ImgDTO> imgDTOList = new ArrayList<>();

        for (ImgEntity imgEntity : imgEntities) {
            imgDTOList.add(this.convertEntityToDTO(imgEntity));
        }
        return imgDTOList;
    }
    // 테스트용 - 이미지 출력 - 진행중
    @Transactional
    public ImgDTO findImg(int num){
        Optional<ImgEntity> imgEntityWrapper = imgRepository.findById(num);
        ImgEntity imgEntity = imgEntityWrapper.get();
        return convertEntityToDTO(imgEntity);
    }
    // 이미지 저장
    public String addImg(List<MultipartFile> files) {
        List<ImgDTO> imgList = null;
        try {
            imgList = fileHandler.parseFileInfo(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!imgList.isEmpty()){
            List<ImgDTO> beans = new ArrayList<>();
            for(ImgDTO imgDTO : imgList){
                ImgEntity temp = imgRepository.save(imgDTO.toEntity());
                beans.add(convertEntityToDTO(temp));
            }
        }
        return "img save ok!";
    }
    // 이미지 + 게시물 함께 저장 Todo: 수정 필요.
    @Transactional
    public PostEntity addPost(PostDTO postDTO, List<MultipartFile> files) throws Exception{
        // 파일을 저장하고 그 img에 대한 리스트를 저장
        List<ImgDTO> imgList = null;
        try {
            imgList = fileHandler.parseFileInfo(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!imgList.isEmpty()){
            List<ImgDTO> beans = new ArrayList<>();
            for(ImgDTO imgDTO : imgList){
                imgDTO.setPostid(postDTO.getPostnum());
                ImgEntity temp = imgRepository.save(imgDTO.toEntity());
                beans.add(convertEntityToDTO(temp));
            }
        }
        //postDTO.setBoardnum(boardRepository.findByBoardname(postDTO.getBoardname()).get().getBoardnum());
        postDTO.setBoardnum(2);
        postDTO.setWriter(memberRepository.findOneById(postDTO.getUid()).getNickname());
        return postRepository.save(postDTO.toEntity());
    }
    @Transactional
    public List<ImgDTO> findPostImgs(int postid) {
        List<ImgEntity> imgEntities = imgRepository.findAllByPostid(postid);
        List<ImgDTO> imgDTOList = new ArrayList<>();

        for (ImgEntity imgEntity : imgEntities) {
            imgDTOList.add(this.convertEntityToDTO(imgEntity));
        }
        return imgDTOList;
    }
}
