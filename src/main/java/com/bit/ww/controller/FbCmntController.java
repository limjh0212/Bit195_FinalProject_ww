package com.bit.ww.controller;

import com.bit.ww.dto.FbCmntDTO;
import com.bit.ww.service.FbCmnt2Service;
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
    private FbCmnt2Service fbCmnt2Service;

    // 댓글 조회 - 확인용
    @GetMapping("/boards/cmnt/{boardnum}")
    public List list(int boardnum, Model model){
        List<FbCmntDTO> fbCmntList = fbCmntService.getFbCmntList(boardnum);
        model.addAttribute("fbCmntList", fbCmntList);
        return fbCmntList;
    }

    //댓글 등록 - 확인용 - id 값은 필요 없음.
    @PostMapping(value ="/boards/cmntPost")
    public int write(@RequestBody @Validated FbCmntDTO fbCmntDTO){
        return fbCmntService.saveCmnt(fbCmntDTO);
    }

    // 댓글 수정 - 확인용 - 값 모두 필요.
    @PutMapping("/boards/cmntEdit/{num}")
    public String update(@RequestBody @Validated FbCmntDTO fbCmntDTO){
        fbCmntService.saveCmnt(fbCmntDTO);
        return "update ok!";
    }
    // 댓글 삭제(대댓글이 없을 경우)
    @DeleteMapping("/boards/cmntDelete/{num}")
    public String deleteCmnt(@PathVariable("num") int num){
        fbCmntService.deleteCmnt(num);
        return "deleteCmnt ok!";
    }

    // 댓글 삭제(대댓글이 있을 경우) - 값 변경
    @PutMapping("/boards/cmntDelete/{num}")
    public String delete(@RequestBody @Validated FbCmntDTO fbCmntDTO){
        FbCmntDTO delCmntDTO = fbCmntDTO;
        boolean exists = fbCmnt2Service.existCmnt2(delCmntDTO.getNum());
        if(exists == true) {
            delCmntDTO.setWriter("deleted");
            delCmntDTO.setContent("deleted");
            fbCmntService.saveCmnt(delCmntDTO);
            return "delete ok!";
        } else {
            return deleteCmnt(delCmntDTO.getNum());
        }
    }
}
