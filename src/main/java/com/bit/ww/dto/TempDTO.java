package com.bit.ww.dto;

import com.bit.ww.entity.TempEntity;
import lombok.*;

import java.time.LocalDateTime;

// 수정완료
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TempDTO {
    private int num;
    private int boardnum;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regdate;

    public TempEntity toEntity() {
        return TempEntity.builder()
                .num(this.num)
                .boardnum(this.boardnum)
                .writer(this.writer)
                .title(this.title)
                .content(this.content)
                .build();
    }

    @Builder
    public TempDTO(int num, int boardnum, String writer, String title, String content, LocalDateTime regdate){
        this.num = num;
        this.boardnum = boardnum;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
    }
}
