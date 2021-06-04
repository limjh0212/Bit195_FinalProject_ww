package com.bit.ww.controller;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.dto.QuestionDTO;
import com.bit.ww.entity.QuestionEntity;
import com.bit.ww.service.CmntService;
import com.bit.ww.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {
    private QuestionService questionService;
    private CmntService cmntService;

    // Todo: 확인필요.
    // 게시물 전체 조회
    @GetMapping("/findAll")
    public List findAll(){
        return questionService.getQuestionList();
    }
    // 게시물 전체 조회 개수
    @GetMapping("/cntFindAll")
    public int cntFindAll(){
        return questionService.countList();
    }
    // Todo : 글 등록도 CmntyController에서 할 예정
    // 글 등록 - id 값은 필요 없음.
    @PostMapping("/post")
    public QuestionEntity write(@RequestBody @Validated QuestionDTO questionDTO){
        return questionService.savePost(questionDTO);
    }

    // 글 수정
    @PatchMapping("/update/{num}")
    public String update(@RequestBody @Validated QuestionDTO questionDTO){
        questionDTO.setTitle(questionDTO.getTitle());
        questionDTO.setContent(questionDTO.getContent());
        questionDTO.setImg(questionDTO.getImg());
        questionService.savePost(questionDTO);
        return "update ok!";
    }

    // 글 상세보기
    @GetMapping("/detail/{num}")
    public HashMap detail(@PathVariable("num") int num) {
        HashMap<String, Object> detail = new HashMap<>();
        QuestionDTO question = questionService.getPost(num);

        detail.put("question", question);

        CmntDTO answer = cmntService.getAnswer(4, num);
        detail.put("answer", answer);

        return detail;
    }
    //1. 최근 글 목록
    @GetMapping("/recentList")
    public List recentList(LocalDateTime start, LocalDateTime end) {
        LocalDateTime startdate = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0,0,0));
        LocalDateTime enddate = LocalDateTime.now();
        return questionService.getRecentList(startdate,enddate);
    }
    @GetMapping("/cntRecentList")
    public int cntRecentList(LocalDateTime start, LocalDateTime end) {
        LocalDateTime startdate = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0,0,0));
        LocalDateTime enddate = LocalDateTime.now();
        return questionService.countRecentList(startdate,enddate);
    }
    //2. 미답변 글 목록
    @GetMapping("/unanswered")
    public List unansweredList() {
        return questionService.getListByIsansweredIsFalse();
    }
    @GetMapping("/cntUnanswered")
    public int cntUnansweredList() {
        return questionService.countListByIsansweredIsFalse();
    }
    // 글 삭제
    @DeleteMapping("/delete/{num}")
    public String delete(@PathVariable("num") int num){
        questionService.deletePost(num);
        return "delete ok!";
    }

    // 글 검색 - 작성자
    @GetMapping("/searchByWriter/{writer}")
    public List searchByWriterList(@PathVariable("writer") String writer){
        return questionService.getListByWriter(writer);
    }
    // 자유게시판 내 글보기 개수
    @GetMapping("/cntSearchByWriter/{writer}")
    public int cntSearchByWriterList(@PathVariable("writer") String writer){
        return questionService.countListByWriter(writer);
    }
}
