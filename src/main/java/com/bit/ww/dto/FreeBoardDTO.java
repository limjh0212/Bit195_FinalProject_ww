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


    public FreeBoardEntity toEntity() {
        return FreeBoardEntity.builder()
                .num(this.num)
                .writer(this.writer)
                .title(this.title)
                .content(this.content)
                .editdate(this.editdate)
                .readcount(this.readcount)
                .build();
    }

    @Builder
    public FreeBoardDTO(int num, String writer, String title, String content, LocalDateTime regdate, LocalDateTime editdate, int readcount){
        this.num = num;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.editdate = editdate;
        this.readcount = readcount;
    }

}
