package com.bit.ww.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "member")
@Getter
public class MemberEntity {
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
    private String img;
    @Column //접근권한
    private String role;
    @Column //등록일자
    private Timestamp regdate;
    @Column //수정일자
    private Timestamp editdate;
    @Column //탈퇴일자
    private Timestamp wthdrdate;
    @Column //탈퇴여부
    private boolean iswithdrawal;

}
