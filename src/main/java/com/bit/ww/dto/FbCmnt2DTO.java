package com.bit.ww.dto;

import com.bit.ww.entity.FbCmnt2Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FbCmnt2DTO {
    private int num;
    private int cmntnum;
    private String writer;
    private String content;
    private LocalDateTime regdate;
    private LocalDateTime editdate;

    public FbCmnt2Entity toEntity() {
        return FbCmnt2Entity.builder()
                .num(this.num)
                .cmntnum(this.cmntnum)
                .writer(this.writer)
                .content(this.content)
                .build();
    }

    @Builder
    public FbCmnt2DTO(int num, int cmntnum, String writer, String content, LocalDateTime regdate, LocalDateTime editdate){
        this.num = num;
        this.cmntnum = cmntnum;
        this.writer = writer;
        this.content = content;
        this.regdate = regdate;
        this.editdate = editdate;
    }
}
