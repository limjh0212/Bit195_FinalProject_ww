package com.bit.ww.dto;

import com.bit.ww.entity.MemberEntity;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {
    private String id;
    private String pw;
    private String nickname;
    private String email;
    private boolean gender;
    private int age;
    private String img;
    private String role;
    private LocalDateTime regdate;
    private LocalDateTime editdate;
    private Timestamp wthdrdate;
    private boolean iswithdrawal;

    public MemberEntity toEntity() { //Todo : TimeEntity 변수 사용 불가
        MemberEntity memberEntity = MemberEntity.builder()
                .id(id)
                .pw(pw)
                .nickname(nickname)
                .email(email)
                .gender(gender)
                .age(age)
                .img(img)
                .role(role)
//                .regdate(regdate)
//                .editdate(editdate)
                .wthdrdate(wthdrdate)
                .iswithdrawal(iswithdrawal)
                .build();
        return memberEntity;
    }

    @Builder
    public MemberDTO(String id, String pw, String nickname, String email, boolean gender, int age, String img, String role, Timestamp wthdrdate, boolean iswithdrawal) {
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
