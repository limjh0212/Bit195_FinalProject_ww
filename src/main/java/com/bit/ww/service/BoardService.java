package com.bit.ww.service;

import com.bit.ww.dto.BoardDTO;
import com.bit.ww.dto.PostDTO;
import com.bit.ww.entity.BoardEntity;
import com.bit.ww.entity.PostEntity;
import com.bit.ww.repository.BoardRepository;
import com.bit.ww.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@DynamicUpdate
public class BoardService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    // 게시판 이름으로 게시판 조회
    @Transactional
    public BoardDTO findBoard(String boardname){
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findByBoardname(boardname);
        BoardEntity boardEntity = boardEntityWrapper.get();
        BoardDTO boardDTO= BoardDTO.builder()
                .boardnum(boardEntity.getBoardnum())
                .boardname(boardEntity.getBoardname())
                .lastnum(boardEntity.getLastnum())
                .build();
        return boardDTO;
    }

    // 게시판 이름으로 게시물 리스트 조회
    @Transactional
    public List<PostDTO> findPosts(String boardname){
        List<PostEntity> postEntities = postRepository.findByBoardnameOrderByNumDesc(boardname);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            PostDTO postDTO = PostDTO.builder()
                    .num(postEntity.getNum())
                    .boardname(postEntity.getBoardname())
                    .boardnum(postEntity.getBoardnum())
                    .postnum(postEntity.getPostnum())
                    .uid(postEntity.getUid())
                    .writer(postEntity.getWriter())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .img(postEntity.getImg())
                    .readcount(postEntity.getReadcount())
                    .isanswered(postEntity.isIsanswered())
                    .istemp(postEntity.isIstemp())
                    .tempnum(postEntity.getTempnum())
                    .regdate(postEntity.getRegdate())
                    .editdate(postEntity.getEditdate())
                    .build();

            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
    @Transactional
    public int cntPosts(String boardname){
        return postRepository.countAllByBoardname(boardname);
    }
    // 게시물 단건 조회
    @Transactional
    public PostDTO getPost(int num){
        Optional<PostEntity> postEntityWrapper = postRepository.findById(num);
        PostEntity postEntity = postEntityWrapper.get();

        PostDTO postDTO = PostDTO.builder()
                .num(postEntity.getNum())
                .boardname(postEntity.getBoardname())
                .boardnum(postEntity.getBoardnum())
                .postnum(postEntity.getPostnum())
                .uid(postEntity.getUid())
                .writer(postEntity.getWriter())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .img(postEntity.getImg())
                .readcount(postEntity.getReadcount())
                .isanswered(postEntity.isIsanswered())
                .istemp(postEntity.isIstemp())
                .tempnum(postEntity.getTempnum())
                .regdate(postEntity.getRegdate())
                .editdate(postEntity.getEditdate())
                .build();
        return postDTO;
    }
    // 게시물 등록 - 게시물 등록 시 해당 게시판의 마지막 번호에 +1
    @Transactional
    public PostEntity savePost(PostDTO postDTO){
        if (postDTO.getBoardname().equals("freeBoard")){
            postDTO.setBoardnum(1);
        }else if (postDTO.getBoardname().equals("OOTD")){
            postDTO.setBoardnum(2);
        }else if (postDTO.getBoardname().equals("hotDeal")){
            postDTO.setBoardnum(3);
        }else if (postDTO.getBoardname().equals("qna")){
            postDTO.setBoardnum(4);
        }else if (postDTO.getBoardname().equals("temp")){
            postDTO.setBoardnum(5);
        }
        return postRepository.save(postDTO.toEntity());
    }
    // 게시물 등록 시 마지막 번호 수정 용
    @Transactional
    public BoardEntity saveBoard(BoardDTO boardDTO){
        return boardRepository.save(boardDTO.toEntity());
    }
    // 게시물 삭제 - 게시물 등록자와 로그인 회원정보가 다르면 실패
    @Transactional
    public void deletePost(int num){
        postRepository.deleteById(num);
    }

}
