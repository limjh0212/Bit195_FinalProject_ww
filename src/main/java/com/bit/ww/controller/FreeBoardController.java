package com.bit.ww.controller;

import com.bit.ww.dto.FreeBoardDTO;
import com.bit.ww.service.FreeBoardService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FreeBoardController {
    private FreeBoardService freeBoardService;


    // 조회 (완료)
    @GetMapping("/boards")
    public List list(Model model){
        List<FreeBoardDTO> freeboardList = freeBoardService.getFreeBoardList();
        model.addAttribute("freeBoardList", freeboardList);
        return freeboardList;
    }

    //글 등록 (완료) - id 값은 필요 없음.
    @PostMapping(value ="/boards/post")
    public int write(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        return freeBoardService.savePost(freeBoardDTO);
    }

    // 글 수정 (완료) - 값 모두 필요.
    @PutMapping("/boards/edit/{num}")
    public String update(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        freeBoardService.savePost(freeBoardDTO);
        return "update ok!";
    }

    // 글 상세보기 (완료)
    @GetMapping("/boards/detail/{num}")
    public String detail(@PathVariable("num") int num, Model model){
        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
        model.addAttribute("freeBoardDTO", freeBoardDTO);
        //확인용
        String fbStr = freeBoardDTO.toString();
        return fbStr;
    }

    // 글 삭제 (완료)
    @DeleteMapping("/boards/delete/{num}")
    public String delete(@PathVariable("num") int num){
        freeBoardService.deletePost(num);
        return "delete ok!";
    }

}
