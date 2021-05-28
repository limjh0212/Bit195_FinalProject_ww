package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

// 수정 완료
@Entity
@Table(name = "free_board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardEntity extends PostTimeEntity{
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

    @Column
    private LocalDateTime editdate;

    @Column
    @ColumnDefault("0")
    private int readcount;

    @Builder
    public FreeBoardEntity(int num, String writer, String title, String content, LocalDateTime editdate, int readcount){
        this.num = num;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.editdate = editdate;
        this.readcount = readcount;
    }

}
