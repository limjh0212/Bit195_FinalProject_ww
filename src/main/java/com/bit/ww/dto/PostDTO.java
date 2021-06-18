package com.bit.ww.dto;

import com.bit.ww.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter // @ModelAttribute를 위해 필요 -> parameter 값으로 DTO객체를 바인딩하는 방식이기 때문이다.
@ToString
@NoArgsConstructor
public class PostDTO {
    private int num;
    private String boardname;
    private int boardnum;
    private int postnum;
    private String uid;
    private String writer;
    private String title;
    private String content;
    private int img;
    private int readcount;
    private int likecount;
    private boolean isanswered;
    private boolean istemp;
    private int tempnum;
    private LocalDateTime regdate;
    private LocalDateTime editdate;

    // Todo: 사용하지 않으면 삭제 예정.
//    public PostDTO(String boardname, String uid, String title, String content) {
//        this.boardname = boardname;
//        this.uid = uid;
//        this.title = title;
//        this.content = content;
//    }

    public PostEntity toEntity() {
        return PostEntity.builder()
                .num(this.num)
                .boardname(this.boardname)
                .boardnum(this.boardnum)
                .postnum(this.postnum)
                .uid(this.uid)
                .writer(this.writer)
                .title(this.title)
                .content(this.content)
                .img(this.img)
                .readcount(this.readcount)
                .likecount(this.likecount)
                .isanswered(this.isanswered)
                .istemp(this.istemp)
                .tempnum(this.tempnum)
                .editdate(this.editdate)
                .build();
    }

    @Builder
    public PostDTO(int num, String boardname, int boardnum, int postnum, String uid, String writer, String title, String content, int img, int readcount, int likecount, boolean isanswered, boolean istemp, int tempnum, LocalDateTime regdate, LocalDateTime editdate){
        this.num = num;
        this.boardname = boardname;
        this.boardnum = boardnum;
        this.postnum = postnum;
        this.uid = uid;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.img = img;
        this.readcount = readcount;
        this.likecount = likecount;
        this.isanswered = isanswered;
        this.istemp = istemp;
        this.tempnum = tempnum;
        this.regdate = regdate;
        this.editdate = editdate;
    }
}
