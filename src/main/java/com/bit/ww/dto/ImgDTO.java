package com.bit.ww.dto;

import com.bit.ww.entity.ImgEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImgDTO {
    private int num;
    private int boardid;
    private int postid;
    private String filename;
    private String originalname;
    private String storedpath;
    private long filesize;

    public ImgEntity toEntity(){
        return ImgEntity.builder()
                .num(this.num)
                .boardid(this.boardid)
                .postid(this.postid)
                .filename(this.filename)
                .originalname(this.originalname)
                .storedpath(this.storedpath)
                .filesize(this.filesize)
                .build();
    }

    @Builder
    public ImgDTO(int num, int boardid, int postid, String filename, String originalname, String storedpath, long filesize){
        this.num = num;
        this.boardid = boardid;
        this.postid = postid;
        this.filename = filename;
        this.originalname = originalname;
        this.storedpath = storedpath;
        this.filesize = filesize;
    }
}
