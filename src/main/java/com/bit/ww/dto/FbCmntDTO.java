package com.bit.ww.dto;

import com.bit.ww.entity.FbCmntEntity;
import com.bit.ww.entity.FreeBoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FbCmntDTO {
    private int num;
    private int boardnum;
    private String writer;
    private String content;
    private LocalDateTime regdate;
    private LocalDateTime editdate;

    public FbCmntEntity toEntity() {
        return FbCmntEntity.builder()
                .num(this.num)
                .boardnum(this.boardnum)
                .writer(this.writer)
                .content(this.content)
                .build();
    }

    @Builder
    public FbCmntDTO(int num, int boardnum, String writer, String content, LocalDateTime regdate, LocalDateTime editdate){
        this.num = num;
        this.boardnum = boardnum;
        this.writer = writer;
        this.content = content;
        this.regdate = regdate;
        this.editdate = editdate;
    }
}
