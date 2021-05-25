package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "free_cmnts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FbCmntEntity extends TimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private int boardnum;

    @Column
    private String writer;

    @Column
    private String content;

    @Builder
    public FbCmntEntity(int num, int boardnum, String writer, String content){
        this.num = num;
        this.boardnum = boardnum;
        this.writer = writer;
        this.content = content;
    }
}
