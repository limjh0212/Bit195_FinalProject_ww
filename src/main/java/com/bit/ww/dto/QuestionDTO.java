package com.bit.ww.dto;

import com.bit.ww.entity.QuestionEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionDTO {
    private int num;
    private String writer;
    private String title;
    private String content;
    private String img;
    private boolean isanswered;
    private LocalDateTime regdate;

    public QuestionEntity toEntity() {
        return QuestionEntity.builder()
                .num(this.num)
                .writer(this.writer)
                .title(this.title)
                .content(this.content)
                .img(this.img)
                .isanswered(this.isanswered)
                .build();
    }

    @Builder
    public QuestionDTO(int num, String writer, String title, String content, String img, boolean isanswered, LocalDateTime regdate){
        this.num = num;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.img = img;
        this.isanswered = isanswered;
        this.regdate = regdate;
    }
}
