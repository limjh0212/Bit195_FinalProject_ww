package com.bit.ww.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
// Todo: 사용하지 않으면 삭제 예정.
@Data
public class PostImgDTO {
    private String boardname;
    private String uid;
    private String title;
    private String content;
    private List<MultipartFile> files;
}
