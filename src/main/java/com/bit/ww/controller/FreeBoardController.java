package com.bit.ww.controller;

import com.bit.ww.dto.FreeBoardDTO;
import com.bit.ww.service.FreeBoardService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
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

    // 글 작성 폼
    @GetMapping("/boards/post")
    public String write(){
        return "postForm";
    }

    //글 등록 Todo: PostMapping 오류 수정 필요
    @PostMapping("/boards/post")
    public String write(@RequestBody FreeBoardDTO freeBoardDTO){
        freeBoardService.savePost(freeBoardDTO);
        return "post";
    }

    // 글 수정 폼
    @RequestMapping(value = "/boards/edit/{num}", method = RequestMethod.GET)
    public String edit(@PathVariable("num") int num, Model model){
        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
        model.addAttribute("freeBoardDTO", freeBoardDTO);
        return "editForm";
    }

    // 글 수정 Todo: 수정 확인 필요
    @RequestMapping(value = "/boards/edit/{num}", method = {RequestMethod.POST, RequestMethod.PUT})
    public String update(FreeBoardDTO freeBoardDTO){
        freeBoardService.savePost(freeBoardDTO);
        return "update";
    }

    // 글 상세보기 (완료)
    @RequestMapping(value = "/boards/detail/{num}", method = RequestMethod.GET)
    public String detail(@PathVariable("num") int num, Model model){
        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
        model.addAttribute("freeBoardDTO", freeBoardDTO);
        //확인용
        String fbStr = freeBoardDTO.toString();
        return fbStr;
    }

    // 글 삭제 (완료) Todo: PostMapping으로 바꿔도 가능한지 확인 필요
    @GetMapping("/boards/delete/{num}")
    public String delete(@PathVariable("num") int num){
        freeBoardService.deletePost(num);
        return "delete";
    }
    //    @RequestMapping(value = "/boards/detail/{num}/del", method = {RequestMethod.POST, RequestMethod.DELETE})
//    public String delete(@PathVariable("num") int num){
//        freeBoardService.deletePost(num);
//        return "delete";
//    }
}
