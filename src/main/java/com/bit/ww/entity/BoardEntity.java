package com.bit.ww.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "board_info")
@NoArgsConstructor
public class BoardEntity {
    @Id
    @Column
    private int boardnum;

    @Column
    private String boardname;

    @Column
    @ColumnDefault("0")
    private int lastnum;

    @Builder
    public BoardEntity(int boardnum, String boardname, int lastnum){
        this.boardnum = boardnum;
        this.boardname = boardname;
        this.lastnum = lastnum;
    }
}
