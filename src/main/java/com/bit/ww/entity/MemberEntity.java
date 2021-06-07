package com.bit.ww.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "member")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // PostEntity에서 User와의 관계를 Json으로 변환할 때 오류를 방지하기 위함.
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
    private String img;
    @ApiModelProperty(hidden = true)
    @Column //탈퇴여부
    private boolean iswithdrawal;

    @Builder
    public MemberEntity(String id, String pw, String nickname, String email, String role, boolean gender, Integer age, String img, boolean iswithdrawal) {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.img = img;
        this.iswithdrawal = iswithdrawal;
    }

}
