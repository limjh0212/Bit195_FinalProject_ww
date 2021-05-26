package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "free_board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardEntity extends TimeEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private String writer;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @ColumnDefault("0")
    private int readcount;

    @Column
    @ColumnDefault("0")
    private boolean istemp;

    @Builder
    public FreeBoardEntity(int num, String writer, String title, String content, int readcount, boolean istemp){
        this.num = num;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.readcount = readcount;
        this.istemp = istemp;
    }

}
