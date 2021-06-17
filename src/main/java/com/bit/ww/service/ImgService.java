package com.bit.ww.service;

import com.bit.ww.dto.ImgDTO;
import com.bit.ww.dto.PostDTO;
import com.bit.ww.entity.ImgEntity;
import com.bit.ww.entity.PostEntity;
import com.bit.ww.repository.ImgRepository;
import com.bit.ww.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImgService {
    private final ImgRepository imgRepository;
    private final PostRepository postRepository;
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
    @Transactional
    public ImgEntity saveImg(ImgDTO imgDTO) {
        return imgRepository.save(imgDTO.toEntity());
    }
    @Transactional
    public List<ImgDTO> findImgs(int postid) {
        List<ImgEntity> imgEntities = imgRepository.findAllByPostid(postid);
        List<ImgDTO> imgDTOList = new ArrayList<>();

        for (ImgEntity imgEntity : imgEntities) {
            imgDTOList.add(this.convertEntityToDTO(imgEntity));
        }
        return imgDTOList;
    }

    @Transactional
    public PostEntity addPost(PostDTO postDTO, List<MultipartFile> files) throws Exception{
        // 파일을 저장하고 그 img에 대한 리스트를 저장
        List<ImgDTO> list = fileHandler.parseFileInfo(postDTO.getNum(), files);
        if(list.isEmpty()){
            postDTO.setImg(0);
        }else{
            List<ImgDTO> imgBeans = new ArrayList<>();
            for(ImgDTO imgDTO : list) {
                ImgEntity temp = imgRepository.save(imgDTO.toEntity());
                imgBeans.add(this.convertEntityToDTO(temp));
            }
            // 수정 필요
            int imgnum = 0;
            for ( int i = 0; i<list.size(); i++){
                imgnum = list.get(i).getNum();
            };
            postDTO.setImg(imgnum);
        }
        return postRepository.save(postDTO.toEntity());
    }
}
