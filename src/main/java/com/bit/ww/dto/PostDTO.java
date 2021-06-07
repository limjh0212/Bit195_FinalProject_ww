package com.bit.ww.dto;

import com.bit.ww.entity.FreeBoardEntity;
import com.bit.ww.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter // @ModelAttribute를 위해 필요 -> parameter 값으로 DTO객체를 바인딩하는 방식이기 때문이다.
@ToString
@NoArgsConstructor // 어떻게 수정할 지 고민중..
public class PostDTO {
    private int postnum;
    private String title;
    private String content;
    private String img;
    private int readcount;
    private boolean isanswered;
    private boolean istemp;
    private int tempnum;
    private LocalDateTime editdate;
}
