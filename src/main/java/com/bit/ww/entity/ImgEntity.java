package com.bit.ww.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ww_image")
@Getter
@NoArgsConstructor
public class ImgEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private int boardid;

    @Column
    private int postid;

    @Column
    private String filename;

    @Column
    private String originalname;

    @Column
    private String storedpath;

    @Column
    private long filesize;

    @Builder
    public ImgEntity (int num, int boardid, int postid, String filename, String originalname, String storedpath, long filesize){
        this.num = num;
        this.boardid = boardid;
        this.postid = postid;
        this.filename = filename;
        this.originalname = originalname;
        this.storedpath = storedpath;
        this.filesize = filesize;
    }
}
