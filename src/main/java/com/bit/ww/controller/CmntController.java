package com.bit.ww.controller;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.service.CmntService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CmntController {
    // Todo: 전체 확인 필요.
    private CmntService cmntService;

    // 댓글 등록(완료) - 확인용 - id 값은 필요 없음.
    @PostMapping(value ="/cmntPost")
    public int create(@RequestBody @Validated CmntDTO cmntDTO){
        return cmntService.saveCmnt(cmntDTO);
    }
    // 대댓글 등록(완료) - Todo: 추후 view에서 boardnum, postnum, writer 가져오고, 댓글에서 대댓글 달기 클릭 시, 댓글의 num을 cmntnum으로 가져올 것.
    @PostMapping(value ="/cmnt2Post")
    public int create2(@RequestParam("cmntnum") int cmntnum, @RequestBody @Validated CmntDTO cmntDTO){
        CmntDTO cmnt2DTO = cmntDTO;
        cmnt2DTO.setDepth(1);
        cmnt2DTO.setCmntnum(cmntnum);
        return cmntService.saveCmnt(cmnt2DTO);
    }

    // 댓글 수정 - 확인용 - 값 모두 필요.
    @PutMapping("/cmntEdit/{num}")
    public String update(@RequestBody @Validated CmntDTO cmntDTO){
        cmntService.saveCmnt(cmntDTO);
        return "update ok!";
    }
    // 댓글 삭제(대댓글이 없을 경우)
    @DeleteMapping("/cmntDelete/{num}")
    public String deleteCmnt(@PathVariable("num") int num){
        cmntService.deleteCmnt(num);
        return "deleteCmnt ok!";
    }

    // 댓글 삭제(대댓글이 있을 경우) - 값 변경
    @PutMapping("/cmntDelete/{num}")
    public String delete(@RequestBody @Validated CmntDTO cmntDTO){
        CmntDTO delCmntDTO = cmntDTO;
        boolean existCmnt2 = cmntService.existCmnt2(delCmntDTO.getNum());
        if(existCmnt2 == true) {
            delCmntDTO.setWriter("deleted");
            delCmntDTO.setContent("deleted");
            cmntService.saveCmnt(delCmntDTO);
            return "delete ok!";
        } else {
            return deleteCmnt(delCmntDTO.getNum());
        }
    }
}
