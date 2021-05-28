package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "board_likes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private int boardnum;

    @Column
    private int postnum;

    @Column
    private String userid;

    @Column
    private boolean isliked;

    @Builder
    public LikeEntity(int num, int boardnum, int postnum, String userid, boolean isliked){
        this.num = num;
        this.boardnum = boardnum;
        this.postnum = postnum;
        this.userid = userid;
        this.isliked = isliked;
}}

