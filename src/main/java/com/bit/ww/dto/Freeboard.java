package com.bit.ww.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "free_board")
@Data
public class Freeboard {
    @Id
    @Column
    private int num;
    @Column
    private String writer;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private byte img;
    @Column
    private Timestamp regdate;
    @Column
    private Timestamp editdate;
    @Column
    private int readcount;
    @Column
    private boolean istemp;
}
