package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends PostTimeEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private String boardname;

    @Column
    private int boardnum;

    @Column
    private int postnum;

    @Column
    private String uid;

    @Column
    private String writer;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String img;

    @Column
    @ColumnDefault("0")
    private int readcount;

    @Column
    @ColumnDefault("0")
    private boolean isanswered;

    @Column
    @ColumnDefault("0")
    private boolean istemp;

    @Column
    @ColumnDefault("0")
    private int tempnum;

    @Column
    private LocalDateTime editdate;

    // 생성자
    @Builder
    public PostEntity (int num, String boardname, int boardnum, int postnum, String uid, String writer, String title, String content, String img, int readcount, boolean isanswered, boolean istemp, int tempnum, LocalDateTime editdate){
        this.num = num;
        this.boardname = boardname;
        this.boardnum = boardnum;
        this.postnum = postnum;
        this.uid = uid;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.img = img;
        this.readcount = readcount;
        this.isanswered = isanswered;
        this.istemp = istemp;
        this.tempnum = tempnum;
        this.editdate = editdate;
    }

}