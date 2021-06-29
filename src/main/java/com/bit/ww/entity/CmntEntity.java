package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "board_cmnts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CmntEntity extends TimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private int boardnum;

    @Column
    private int postnum;

    @Column
    @ColumnDefault("0")
    private int cmntnum;

    @Column
    @ColumnDefault("0")
    private int depth;

    @Column
    private String writer;

    @Column
    private String content;

    @Builder
    public CmntEntity(int num, int boardnum, int postnum, int cmntnum, int depth, String writer, String content) {
        this.num = num;
        this.boardnum = boardnum;
        this.postnum = postnum;
        this.cmntnum = cmntnum;
        this.depth = depth;
        this.writer = writer;
        this.content = content;
    }
}
