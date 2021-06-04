package com.bit.ww.controller;

import com.bit.ww.dto.PostDTO;
import com.bit.ww.entity.BoardEntity;
import com.bit.ww.entity.PostEntity;
import com.bit.ww.service.BoardService;
import com.bit.ww.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/cmnty")
public class BoardController {
    private final BoardService boardService;
    private final LikeService likeService;

    // 게시판 정보 조회
    @GetMapping("/{boardname}")
    public BoardEntity boardInfo(@PathVariable String boardname){
        return boardService.findBoard(boardname);
    }
    // 게시판 글 리스트
    @GetMapping("/{boardname}/posts")
    public HashMap posts(@PathVariable String boardname){
        HashMap<String, Object> posts = new HashMap<>();
        posts.put("posts", boardService.findPosts(boardname));
        //posts.put("cntPosts", boardService.cntPosts(boardname));
        return posts;
    }
    // 게시판 글 작성
    @PostMapping("/{boardname}")
    public PostEntity post(@PathVariable String boardname, @Validated @RequestBody PostDTO postDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uid = auth.getName();
//        if (boardname=="freeBoard"){
//            postDTO.setBoardnum(1);
//        }else if (boardname=="OOTD"){
//            postDTO.setBoardnum(2);
//        }else if (boardname=="hotDeal"){
//            postDTO.setBoardnum(3);
//        }else if (boardname=="qna"){
//            postDTO.setBoardnum(4);
//        }else{
//            temp(boardname,postDTO);
//        }
        return boardService.writePost(uid, boardname, postDTO);
    }
    // 임시저장
    @PostMapping("/temp/{boardname}")
    public PostEntity temp(@PathVariable String boardname, @Validated @RequestBody PostDTO postDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uid = auth.getName();
        if (boardname=="freeBoard"){
            postDTO.setTempnum(1);
        }else if (boardname=="OOTD"){
            postDTO.setTempnum(2);
        }else if (boardname=="hotDeal"){
            postDTO.setTempnum(3);
        }else if (boardname=="qna"){
            postDTO.setTempnum(4);
        }
        return boardService.writePost(uid, "temp", postDTO);
    }
    // 게시물 상세보기
    @GetMapping("/post/{postId}")
    public PostEntity post(@PathVariable("postId") int num){
        return boardService.getPost(num);
    }
    // 게시판 글 수정
    @PatchMapping("/post/{postId}")
    public PostEntity post(@PathVariable("postId") int num, @Validated @RequestBody PostDTO postDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uid = auth.getName();
        return boardService.updatePost(num, uid, postDTO);
    }
    // 게시판 글 삭제
    @DeleteMapping("/post/{postId}")
    public String deletepost(@PathVariable("postId") int num){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uid = auth.getName();
        boardService.deletePost(num, uid);
        return "delete ok!";
    }
}
