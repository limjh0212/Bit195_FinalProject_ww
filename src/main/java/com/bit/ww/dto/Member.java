package com.bit.ww.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "member")
public class Member {
    @Id
    private String id;
    @Column
    private String pw;
    @Column
    private String nickname;
    @Column
    private String email;
    @Column
    private boolean gender;
    @Column
    private int age;
    @Column
    private byte img;
    @Column
    private Timestamp regdate;
    @Column
    private Timestamp editdate;
    @Column
    private Timestamp wthdrdate;
    @Column
    private boolean iswithrawal;
}
