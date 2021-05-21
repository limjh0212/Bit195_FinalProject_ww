package com.bit.ww.dto;

import com.bit.ww.entity.FreeBoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FreeBoardDTO {
    private int num;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private LocalDateTime editdate;
    private int readcount;
    private boolean istemp;

    public FreeBoardEntity toEntity() {
        return FreeBoardEntity.builder()
                .num(this.num)
                .writer(this.writer)
                .title(this.title)
                .content(this.content)
                .readcount(this.readcount)
                .istemp(this.istemp)
                .build();
    }

    @Builder
    public FreeBoardDTO(int num, String writer, String title, String content, LocalDateTime regdate, LocalDateTime editdate, int readcount, boolean istemp){
        this.num = num;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.editdate = editdate;
        this.readcount = readcount;
        this.istemp = istemp;
    }
}
