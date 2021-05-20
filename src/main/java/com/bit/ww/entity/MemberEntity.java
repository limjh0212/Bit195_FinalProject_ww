package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends TimeEntity {

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
    @Column //탈퇴일자
    private Timestamp wthdrdate;
    @Column //탈퇴여부
    private boolean iswithdrawal;

    @Builder
    public MemberEntity(String id, String pw, String nickname, String email, boolean gender, int age, String img, String role, Timestamp wthdrdate, boolean iswithdrawal) {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.img = img;
        this.role = role;
        this.wthdrdate = wthdrdate;
        this.iswithdrawal = iswithdrawal;
    }
}
