package com.bit.ww.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MemberDTO {
    private String id;
    private String pw;
    private String nickname;
    private String email;
    private boolean gender;
    private int age;
    private String img;
    private Timestamp regdate;
    private Timestamp editdate;
    private Timestamp wthdrdate;
    private boolean iswithdrawal;
    private String role;

}
