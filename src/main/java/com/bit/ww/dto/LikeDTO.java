package com.bit.ww.dto;

import com.bit.ww.entity.LikeEntity;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeDTO {
    private int num;
    private int boardnum;
    private int postnum;
    private String userid;


    public LikeEntity toEntity() {
        return LikeEntity.builder()
                .num(this.num)
                .boardnum(this.boardnum)
                .postnum(this.postnum)
                .userid(this.userid)
                .build();
    }

    @Builder
    public LikeDTO(int num, int boardnum, int postnum, String userid){
        this.num = num;
        this.boardnum = boardnum;
        this.postnum = postnum;
        this.userid = userid;
    }
}
