package com.bit.ww.controller;

import com.bit.ww.dto.FbCmntDTO;
import com.bit.ww.service.FbCmntService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FbCmntController {
    private FbCmntService fbCmntService;

    // 댓글 조회 - 확인용
    @GetMapping("/boards/comments")
    public List list(Model model){
        List<FbCmntDTO> fbCmntList = fbCmntService.getFbCmntList();
        model.addAttribute("fbCmntList", fbCmntList);
        return fbCmntList;
    }

    // 댓글 조회 - 게시물에 해당하는 댓글 출력 Todo: 해당 메소드 생성

   //댓글 등록 - 확인용 - id 값은 필요 없음.
    @PostMapping(value ="/boards/cmnt")
    public int write(@RequestBody @Validated FbCmntDTO fbCmntDTO){
        return fbCmntService.saveCmnt(fbCmntDTO);
    }

    // 댓글 수정 - 확인용 - 값 모두 필요.
    @PutMapping("/boards/cmntEdit/{num}")
    public String update(@RequestBody @Validated FbCmntDTO fbCmntDTO){
        fbCmntService.saveCmnt(fbCmntDTO);
        return "update ok!";
    }

    // 댓글 삭제(대댓글이 없을 경우) - 확인용
//    @DeleteMapping("/boards/cmntDelete/{num}")
//    public String delete(@PathVariable("num") int num){
//        fbCmntService.deleteCmnt(num);
//        return "delete ok!";
//    }

    // 댓글 삭제(대댓글이 있을 경우) - 값 변경 Todo: (대댓글 존재여부에 따라) if문으로 처리필요.
    @PutMapping("/boards/cmntDelete/{num}")
    public String deleteCmnt(@RequestBody @Validated FbCmntDTO fbCmntDTO){
        FbCmntDTO delCmnt = fbCmntDTO;
        delCmnt.setWriter("deleted");
        delCmnt.setContent("deleted");
        fbCmntService.saveCmnt(delCmnt);
        return "deleteCmnt ok!";
    }
}
