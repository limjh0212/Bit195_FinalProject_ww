package com.bit.ww.dto;

import com.bit.ww.entity.CmntEntity;
import lombok.*;

import java.time.LocalDateTime;

// 수정완료
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CmntDTO {
    private int num;
    private int boardnum;
    private int postnum;
    private int cmntnum;
    private int depth;
    private String writer;
    private String content;
    private LocalDateTime regdate;
    private LocalDateTime editdate;

    public CmntEntity toEntity() {
        return CmntEntity.builder()
                .num(this.num)
                .boardnum(this.boardnum)
                .postnum(this.postnum)
                .cmntnum(this.cmntnum)
                .depth(this.depth)
                .writer(this.writer)
                .content(this.content)
                .build();
    }

    @Builder
    public CmntDTO(int num, int boardnum, int postnum, int cmntnum, int depth, String writer, String content, LocalDateTime regdate, LocalDateTime editdate){
        this.num = num;
        this.boardnum = boardnum;
        this.postnum = postnum;
        this.cmntnum = cmntnum;
        this.depth = depth;
        this.writer = writer;
        this.content = content;
        this.regdate = regdate;
        this.editdate = editdate;
    }
}
