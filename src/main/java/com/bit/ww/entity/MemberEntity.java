package com.bit.ww.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends TimeEntity{
    @ApiModelProperty(example = "ID")
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @ApiModelProperty(example = "Password")
    @Column
    private String pw;
    @ApiModelProperty(example = "NickName")
    @Column
    private String nickname;
    @ApiModelProperty(hidden = true)
    @Column
    private String email;
    @ApiModelProperty(example = "ROLE_USER")
    @Column //접근권한
    private String role;
    @ApiModelProperty(hidden = true)
    @Column //token
    private String token;
    @ApiModelProperty(hidden = true)
    @Column
    private boolean gender;
    @ApiModelProperty(hidden = true)
    @Column
    private Integer age;
    @ApiModelProperty(hidden = true)
    @Column
    @ColumnDefault("0")
    private int img;
    @ApiModelProperty(hidden = true)
    @Column //탈퇴여부
    private boolean iswithdrawal;
    @ApiModelProperty(hidden = true)
    @Column //탈퇴일자
    private LocalDateTime wthdrdate;
    @Builder
    public MemberEntity(String id, String pw, String nickname, String email, String role, boolean gender, Integer age, int img, boolean iswithdrawal, LocalDateTime wthdrdate) {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.img = img;
        this.iswithdrawal = iswithdrawal;
        this.wthdrdate = wthdrdate;
    }

}
