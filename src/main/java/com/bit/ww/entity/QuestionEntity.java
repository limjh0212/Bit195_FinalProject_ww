package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "question_board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionEntity extends PostTimeEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private String writer;

    @Column
    private String title;

    @Column
    private String content;

    // Todo : image 할때 바꿀 예정.
    @Column
    private String img;

    @Column
    @ColumnDefault("0")
    private boolean isanswered;

    @Builder
    public QuestionEntity(int num, String writer, String title, String content, String img, boolean isanswered){
        this.num = num;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.img = img;
        this.isanswered = isanswered;
    }

}
