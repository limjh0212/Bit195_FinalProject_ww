package com.bit.ww.dto;

import com.bit.ww.entity.BoardEntity;
import lombok.*;

@Getter
@Setter // @ModelAttribute를 위해 필요 -> parameter 값으로 DTO객체를 바인딩하는 방식이기 때문이다.
@ToString
@NoArgsConstructor
public class BoardDTO {
    private int boardnum;
    private String boardname;
    private int lastnum;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .boardnum(this.boardnum)
                .boardname(this.boardname)
                .lastnum(this.lastnum)
                .build();
    }
    @Builder
    public BoardDTO(int boardnum, String boardname, int lastnum){
        this.boardnum = boardnum;
        this.boardname = boardname;
        this.lastnum = lastnum;
    }
}
