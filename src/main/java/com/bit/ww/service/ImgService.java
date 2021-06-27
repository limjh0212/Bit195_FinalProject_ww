package com.bit.ww.service;

import com.bit.ww.dto.ImgDTO;
import com.bit.ww.dto.PostDTO;
import com.bit.ww.entity.ImgEntity;
import com.bit.ww.entity.MemberEntity;
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
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final FileHandler fileHandler;

    public ImgDTO convertEntityToDTO(ImgEntity imgEntity) {
    return ImgDTO.builder()
            .num(imgEntity.getNum())
            .boardid(imgEntity.getBoardid())
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
    public String addImg(int boardnum, List<MultipartFile> files) {
        List<ImgDTO> imgList = null;
        try {
            imgList = fileHandler.parseFilesInfo(0, files);
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
    // 이미지 저장
    public int addMember(MultipartFile file) {
        ImgDTO img = null;
        try {
            img = fileHandler.parseFileInfo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if(!img.equals(null)){
//            imgRepository.save(img.toEntity());
//        }
        return imgRepository.save(img.toEntity()).getNum();
    }
    // 이미지 + 게시물 함께 저장 Todo: 수정 필요.
    @Transactional
    public PostEntity addPost(PostDTO postDTO, List<MultipartFile> files) throws Exception{
        // 파일을 저장하고 그 img에 대한 리스트를 저장
        List<ImgDTO> imgList = null;
        int boardnum = boardRepository.findByBoardname(postDTO.getBoardname()).get().getBoardnum();

        try {
            imgList = fileHandler.parseFilesInfo(boardnum,files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!imgList.isEmpty()){
            List<ImgDTO> beans = new ArrayList<>();
            for(ImgDTO imgDTO : imgList){
                imgDTO.setBoardid(boardnum);
                imgDTO.setPostid(postDTO.getPostnum());
                ImgEntity temp = imgRepository.save(imgDTO.toEntity());
                beans.add(convertEntityToDTO(temp));
            }
        }
        postDTO.setBoardnum(boardnum);
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
    @Transactional
    public List<ImgDTO> findOotdImgs() throws Exception{
        List<ImgEntity> imgEntities = imgRepository.findAllByBoardidOrderByNumDesc(2);
        List<Integer> postidList = new ArrayList<>();
        for (int i = 0; i < imgEntities.size(); i++){
            if(!postidList.contains(imgEntities.get(i).getPostid())) {
                System.out.println(imgEntities.get(i).getPostid());
                postidList.add(0, imgEntities.get(i).getPostid());
            }
        }
        List<ImgEntity> imgEntityList = new ArrayList<>();
        for (int k = 0; k < postidList.size(); k++) {
            if (imgRepository.existsAllByBoardidAndPostid(2, postidList.get(k))) {
                imgEntityList.add(0,(imgRepository.findAllByBoardidAndPostidOrderByNumDesc(2, postidList.get(k))).get(0));
            }else{
                //todo: 기본 이미지 출력
            }
        }
        System.out.println("imgEntityList : "+imgEntityList);
        List<ImgDTO> imgDTOList = new ArrayList<>();

        for (ImgEntity imgEntity : imgEntityList) {
            imgDTOList.add(this.convertEntityToDTO(imgEntity));
        }
        return imgDTOList;
    }
    @Transactional
    public List<ImgDTO> findMyOotdImgs(String uid) throws Exception{
        List<ImgEntity> imgEntities = imgRepository.findAllByBoardidOrderByNumDesc(2);
        List<Integer> postidList = new ArrayList<>();
        List<PostEntity> myOotdPostid = postRepository.findAllByBoardnameAndUidOrderByNumDesc("OOTD",uid);
        for (int j = 0; j < myOotdPostid.size();j++){
            postidList.add(0,myOotdPostid.get(j).getPostnum());
        }
//        for (int i = 0; i < imgEntities.size(); i++){
//            if(!postidList.contains(imgEntities.get(i).getPostid())) {
//                System.out.println(imgEntities.get(i).getPostid());
//                postidList.add(0, imgEntities.get(i).getPostid());
//            }
//        }

        List<ImgEntity> imgEntityList = new ArrayList<>();
        for (int k = 0; k < postidList.size(); k++) {
            if (imgRepository.existsAllByBoardidAndPostid(2, postidList.get(k))) {
                imgEntityList.add(0,(imgRepository.findAllByBoardidAndPostidOrderByNumDesc(2, postidList.get(k))).get(0));
            }else{
                //todo: 기본 이미지 출력
            }
        }
        System.out.println("imgEntityList : "+imgEntityList);
        List<ImgDTO> imgDTOList = new ArrayList<>();

        for (ImgEntity imgEntity : imgEntityList) {
            imgDTOList.add(this.convertEntityToDTO(imgEntity));
        }
        return imgDTOList;
    }
    @Transactional
    public String deleteImg(String uid){
        MemberEntity memberEntity = memberRepository.findOneById(uid);
        imgRepository.deleteById(memberEntity.getImg());
        return "delete ok!";
    }
    @Transactional
    public String deleteImgs(int postid){
        List<Integer> numList = new ArrayList<>();
        List<ImgEntity> imgEntityList = imgRepository.findAllByBoardidAndPostidOrderByNumDesc(2,postid);
        for (int i = 0; i< imgEntityList.size(); i++){
            numList.add(0,imgEntityList.get(i).getNum());
        }
        for (int j = 0; j < numList.size();j++){
            imgRepository.deleteById(numList.get(j));
        }
        return "delete ok!";
    }
    @Transactional
    public boolean existImg(int postid){
        return imgRepository.existsAllByBoardidAndPostid(2,postid);
    }
}
