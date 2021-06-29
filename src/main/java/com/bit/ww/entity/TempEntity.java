package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 생성 완료
@Entity
@Table(name = "board_temp")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempEntity extends PostTimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private int boardnum;

    @Column
    private String writer;

    @Column
    private String title;

    @Column
    private String content;

    @Builder
    public TempEntity(int num, int boardnum, String writer, String title, String content) {
        this.num = num;
        this.boardnum = boardnum;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

}
