package com.bit.ww.service;

import com.bit.ww.dto.PostDTO;
import com.bit.ww.entity.BoardEntity;
import com.bit.ww.entity.MemberEntity;
import com.bit.ww.entity.PostEntity;
import com.bit.ww.repository.BoardRepository;
import com.bit.ww.repository.MemberRepository;
import com.bit.ww.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 게시판 이름으로 게시판 조회
    public BoardEntity findBoard(String boardname){
        return Optional.ofNullable(boardRepository.findByBoardname(boardname)).orElseThrow(RuntimeException::new);
    }

    // 게시판 이름으로 게시물 리스트 조회
    public List<PostEntity> findPosts(String boardname){
        return postRepository.findByBoardOrderByNumDesc(findBoard((boardname)));
    }

    // 게시물 단건 조회
    public PostEntity getPost(int num){
       return postRepository.findByNum(num);
    }
    // 게시물 등록 - 게시물 등록 시 해당 게시판의 마지막 번호에 +1
    public PostEntity writePost(String uid, String boardname, PostDTO postDTO){
        BoardEntity board = findBoard(boardname);
        int lastnum = board.getLastnum()+1;
        postDTO.setPostnum(lastnum);
        PostEntity post = new PostEntity(memberRepository.findOneById(uid), board, postDTO.getPostnum(), postDTO.getTitle(), postDTO.getContent(), postDTO.getImg());
        board.setLastnum(lastnum);
        boardRepository.save(board);
        return postRepository.save(post);
    }
    // 게시물 수정 - 게시물 등록자와 로그인 회원정보가 다르면 실패
    public PostEntity updatePost(int num, String uid, PostDTO postDTO){
        PostEntity post = postRepository.findByNum(num);
        MemberEntity member = post.getMember();
        if(!uid.equals(member.getId())){
            throw new RuntimeException();
        }
        postDTO.setEditdate(LocalDateTime.now());
        post.setUpdate(postDTO.getTitle(), postDTO.getContent(), postDTO.getImg(), postDTO.getEditdate());
        return postRepository.save(post);
    }
    // 게시물 삭제 - 게시물 등록자와 로그인 회원정보가 다르면 실패
    public void deletePost(int num, String uid){
        PostEntity post = postRepository.findByNum(num);
        MemberEntity member = post.getMember();
        if(!uid.equals(member.getId())){
            throw new RuntimeException();
        }
        postRepository.deleteById(post.getNum());
    }
}
