package com.bit.ww.controller;

import com.bit.ww.dto.FbCmnt2DTO;
import com.bit.ww.dto.FbCmntDTO;
import com.bit.ww.dto.FreeBoardDTO;
import com.bit.ww.service.FbCmnt2Service;
import com.bit.ww.service.FbCmntService;
import com.bit.ww.service.FreeBoardService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class FreeBoardController {
    private FreeBoardService freeBoardService;
    private FbCmntService fbCmntService;
    private FbCmnt2Service fbCmnt2Service;

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

    // 글 상세보기 (완료) - 해당하는 댓글 목록과 대댓글 목록도 함께 출력
    @GetMapping("/boards/detail/{num}")
    public String detail(@PathVariable("num") int num, Model model){
        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
        model.addAttribute("freeBoardDTO", freeBoardDTO);
        //댓글
        List<FbCmntDTO> fbCmntList = fbCmntService.getFbCmntList(num);
        model.addAttribute("fbCmntList", fbCmntList);
        //대댓글
        //대댓글 자체 > 댓글에 해당하는 대댓글 목록 > 해당 게시물의 댓글의 대댓글 목록
        List<List<FbCmnt2DTO>> fbCmnt2List = new ArrayList<>();
        for(int i = 0; i<fbCmntList.size();i++){
            List<FbCmnt2DTO> temp = fbCmnt2Service.getFbCmnt2List(fbCmntList.get(i).getNum());
            fbCmnt2List.add(temp);
        }
        model.addAttribute("fbCmnt2List", fbCmnt2List);
        return "detail ok!";
    }

    // 글 삭제 (완료)
    @DeleteMapping("/boards/delete/{num}")
    public String delete(@PathVariable("num") int num){
        freeBoardService.deletePost(num);
        return "delete ok!";
    }

}
