package com.bit.ww.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "board_info")
@NoArgsConstructor
@DynamicUpdate
public class BoardEntity {
    @Id
    @Column
    private int boardnum;

    @Column
    private String boardname;

    @Column
    @ColumnDefault("0")
    private int lastnum;

    public BoardEntity setLastnum(int lastnum){
        this.lastnum = lastnum;
        return this;
    }

}
