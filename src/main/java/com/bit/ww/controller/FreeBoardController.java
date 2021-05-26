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

    // 조회 (완료) - 임시저장이 아닌 것만 출력
    @GetMapping("/boards")
    public List list(Model model){
        List<FreeBoardDTO> freeboardList = freeBoardService.getFreeBoardList();
        model.addAttribute("freeBoardList", freeboardList);
        int count = freeBoardService.countList();
        model.addAttribute("count", count);
        return freeboardList;
    }

    // 글 등록 (완료) - id 값은 필요 없음(istemp=0으로 보내기).
    @PostMapping(value ="/boards/post")
    public int write(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        return freeBoardService.savePost(freeBoardDTO);
    }
    // 글 임시저장 등록 (완료) - istemp = 1로 보내기
    @PostMapping(value ="/boards/tempPost")
    public String tempWrite(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        FreeBoardDTO tempDTO = freeBoardDTO;
        tempDTO.setIstemp(true);
        freeBoardService.savePost(tempDTO);
        return "tempPost ok!";
    }

    // 글 임시저장 가져오기 (완료)
    @GetMapping("/boards/post/{num}")
    public String getTemp(@PathVariable("num") int num, Model model){
        FreeBoardDTO tempDTO = freeBoardService.getPost(num);
        model.addAttribute("tempDTO", tempDTO);
        int tempCount = freeBoardService.countListByTemp(tempDTO.getWriter());
        model.addAttribute("tempCount", tempCount);
        return "getTemp ok!";
    }

    // 글 임시저장한 목록 가져오기 (완료) Todo: 로그인세션으로 작성자 id 가져오기 (writer=id) 추가 필요.
    @GetMapping("/boards/post/tempList/{writer}")
    public List tempList(@PathVariable("writer") String writer, Model model){
        List<FreeBoardDTO> tempList = freeBoardService.getTempList(writer);
        model.addAttribute("tempList", tempList);
        int tempCount = freeBoardService.countListByWriter(writer);
        model.addAttribute("tempCount", tempCount);
        return tempList;
    }

    // 글 임시저장 삭제 (완료) Todo: 작성일로부터 일정 시간이 지나면 삭제되는 쿼리 작성 필요.
    @DeleteMapping("/boards/tempDelete/{num}")
    public String tempDelete(@PathVariable("num") int num){
        freeBoardService.deletePost(num);
        return "tempDelete ok!";
    }

    // 글 수정 (완료) - 값 모두 필요.
    @PutMapping("/boards/edit/{num}")
    public String update(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        freeBoardService.savePost(freeBoardDTO);
        return "update ok!";
    }

    // 글 상세보기 (완료) - 해당하는 댓글 목록과 대댓글 목록도 함께 출력, 조회수 증가
    // Todo: 조회수 증가할 때마다 editdate 변경되는 오류 수정필요.
    @GetMapping("/boards/detail/{num}")
    public String detail(@PathVariable("num") int num, Model model){
        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
        int readcount = freeBoardDTO.getReadcount()+1;
        freeBoardDTO.setReadcount(readcount);
        freeBoardService.savePost(freeBoardDTO);
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
