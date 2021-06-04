package com.bit.ww.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends PostTimeEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private int postnum;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String img;

    @Column
    @ColumnDefault("0")
    private int readcount;

    @Column
    @ColumnDefault("0")
    private boolean isanswered;

    @Column
    @ColumnDefault("0")
    private boolean istemp;

    @Column
    @ColumnDefault("0")
    private int tempnum;

    @Column
    private LocalDateTime editdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardnum", referencedColumnName = "boardnum")
    private BoardEntity board;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name="uid", referencedColumnName="id"),
            @JoinColumn(name="writer", referencedColumnName="nickname")
    })
    private MemberEntity member;

    // Join테이블이 Json결과에 표시되지 않도록 처리
    protected BoardEntity getBoardEntity(){
        return board;
    }

    // 생성자
    @Builder
    public PostEntity (MemberEntity member, BoardEntity board, int num, int postnum, String title, String content, String img, int readcount, boolean isanswered, boolean istemp, int tempnum, LocalDateTime editdate){
        this.member = member;
        this.board = board;
        this.num = num;
        this.postnum = postnum;
        this.title = title;
        this.content = content;
        this.img = img;
        this.readcount = readcount;
        this.isanswered = isanswered;
        this.istemp = istemp;
        this.tempnum = tempnum;
        this.editdate = editdate;
    }
    // 생성
    public PostEntity(MemberEntity member, BoardEntity board, int postnum, String title, String content, String img) {
        super();
    }

    // 수정
    public PostEntity setUpdate(String title, String content, String img, LocalDateTime editdate){
        this.title = title;
        this.content = content;
        this.img = img;
        this.editdate = editdate;
        return this;
    }
    // temp 임시저장 생성자
    public PostEntity setTemp(MemberEntity member, BoardEntity board, int postnum, String title, String content, String img, boolean istemp, int tempnum){
        this.member = member;
        this.board = board;
        this.postnum = postnum;
        this.title = title;
        this.content = content;
        this.img = img;
        this.istemp = istemp;
        this.tempnum = tempnum;
        return this;
    }

    // QnA 생성자
    public PostEntity setQnA(MemberEntity member, BoardEntity board, int postnum, String title, String content, String img, boolean isanswered){
        this.member = member;
        this.board = board;
        this.postnum = postnum;
        this.title = title;
        this.content = content;
        this.img = img;
        this.isanswered = isanswered;
        return this;
    }
    // QnA 답변 완료시 수정
    public PostEntity setQnAAnswered(boolean isanswered){
        this.isanswered = isanswered;
        return this;
    }
    // 조회수
    public PostEntity setReadCount(int readCount){
        this.readcount = readCount;
        return this;
    }

}