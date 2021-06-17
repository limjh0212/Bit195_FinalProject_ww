package com.bit.ww.service;

import com.bit.ww.dto.ImgDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileHandler {
    public List<ImgDTO> parseFileInfo(int postid, List<MultipartFile> multipartFiles) throws Exception{
        // 반환할 파일 리스트
        List<ImgDTO> fileList = new ArrayList<>();
        // 파일이 빈 것이 들어오면 빈 객체 반환
        if(multipartFiles.isEmpty()){
            return fileList;
        }
        // 파일 이름이 중복되지 않도록 업로드 날짜로 변경하여 저장
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = simpleDateFormat.format(new Date());
        // 프로젝트 폴더에 저장하기 위해 절대경로 설정 (윈도우 톰캣은 temp 파일 이용함.)
        String absolutePath = new File("").getAbsolutePath()+"\\";
        System.out.print(absolutePath);
        // 경로를 지정한 후 그곳에 저장할 예정.
        String path = "images/"+currentDate;
        File file = new File(path);
        // 저장할 위치의 디렉토리가 존재하지 않을 경우
        if(!file.exists()){
            // mkdir과 다른 점은 상위 디렉토리가 존재하지 않을 때, 상위 디렉토리까지 생성한다는 것.
            file.mkdirs();
        }
        // 파일 수정 과정
        for(MultipartFile multipartFile : multipartFiles){
            // 파일이 비어있을 때의 오류 방지를 위해
            if(!multipartFile.isEmpty()){
                // jpeg, png, gif 파일들만 받을 예정
                String contentType = multipartFile.getContentType();
                String originalFileExtension;
                // 확장자 명이 없으면 잘못된 파일임을 의미
                if (ObjectUtils.isEmpty(contentType)){
                    break;
                }else {
                    if(contentType.contains("image/jpeg")){
                        originalFileExtension = ".jpg";
                    }else if (contentType.contains("image/png")){
                        originalFileExtension = ".png";
                    }else if (contentType.contains("image/gif")){
                        originalFileExtension = ".gif";
                    }else { // 이 외의 파일은 다루지 않는다.
                        break;
                    }
                }
                // 각 이름은 겹치면 안되므로 나노초를 활용하여 새 이름으로 저장
                String newname = Long.toString(System.nanoTime())+originalFileExtension;
                // 생성 후 리스트에 추가
                ImgDTO imgDTO = ImgDTO.builder()
                        .postid(postid)
                        .filename(newname)
                        .originalname(multipartFile.getOriginalFilename())
                        .storedpath(path+"/"+newname)
                        .filesize(multipartFile.getSize())
                        .build();
                fileList.add(imgDTO);

                // 저장된 파일로 변경하여 이를 보여주기 위한 과정
                file = new File(absolutePath+path+"/"+newname);
                multipartFile.transferTo(file);
            }
        }
        System.out.print(fileList);
        return fileList;
    }
}
