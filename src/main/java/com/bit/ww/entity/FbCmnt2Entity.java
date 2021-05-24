package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "free_cmnts2")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FbCmnt2Entity extends TimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private int cmntnum;

    @Column
    private String writer;

    @Column
    private String content;

    @Builder
    public FbCmnt2Entity(int num, int cmntnum, String writer, String content){
        this.num = num;
        this.cmntnum = cmntnum;
        this.writer = writer;
        this.content = content;
    }
}
