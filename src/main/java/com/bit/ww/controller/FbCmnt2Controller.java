package com.bit.ww.controller;

import com.bit.ww.dto.FbCmnt2DTO;
import com.bit.ww.service.FbCmnt2Service;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FbCmnt2Controller {
    private FbCmnt2Service fbCmnt2Service;

    // 대댓글 조회 - 확인용
    @GetMapping("/boards/comments2")
    public List list(Model model){
        List<FbCmnt2DTO> fbCmnt2List = fbCmnt2Service.getFbCmnt2List();
        model.addAttribute("fbCmnt2List", fbCmnt2List);
        return fbCmnt2List;
    }

    // 대댓글 조회 - 각 댓글에 해당하는 대댓글 출력 Todo: 해당 메소드 생성

   // 대댓글 등록 - 확인용 - id 값은 필요 없음.
    @PostMapping(value ="/boards/cmnt2")
    public int write(@RequestBody @Validated FbCmnt2DTO fbCmnt2DTO){
        return fbCmnt2Service.saveCmnt2(fbCmnt2DTO);
    }

    // 대댓글 수정 - 확인용 - 값 모두 필요.
    @PutMapping("/boards/cmnt2Edit/{num}")
    public String update(@RequestBody @Validated FbCmnt2DTO fbCmnt2DTO){
        fbCmnt2Service.saveCmnt2(fbCmnt2DTO);
        return "update ok!";
    }

    // 대댓글 삭제 - 확인용
    @DeleteMapping("/boards/cmnt2Delete/{num}")
    public String delete(@PathVariable("num") int num){
        fbCmnt2Service.deleteCmnt2(num);
        return "delete ok!";
    }

}
