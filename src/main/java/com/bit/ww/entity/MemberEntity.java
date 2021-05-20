package com.bit.ww.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "member")
@Getter
public class MemberEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @Column
    private String pw;
    @Column
    private String nickname;
    @Column
    private String email;
    @Column
    private boolean gender;
    @Column(name = "age", nullable = true)
    private Integer age;
    @Column
    private String img;
    @Column //등록일자
    @CreationTimestamp
    private Timestamp regdate;
    @Column //수정일자
    @UpdateTimestamp
    private Timestamp editdate;
    @Column //탈퇴일자
    private Timestamp wthdrdate;
    @Column //탈퇴여부
    private boolean iswithdrawal;
    @Column //접근권한
    private String role;

}
