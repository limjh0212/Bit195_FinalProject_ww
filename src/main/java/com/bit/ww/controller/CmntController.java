package com.bit.ww.controller;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.dto.QuestionDTO;
import com.bit.ww.entity.CmntEntity;
import com.bit.ww.service.CmntService;
import com.bit.ww.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cmnt")
public class CmntController {
    private CmntService cmntService;
    private QuestionService questionService;
    // Todo: 확인필요.
    // 댓글 등록 - 확인용 - id 값은 필요 없음.
    @PostMapping(value ="/post")
    public CmntEntity create(@RequestBody @Validated CmntDTO cmntDTO){
        return cmntService.saveCmnt(cmntDTO);
    }
    // 대댓글 등록 - Todo: 추후 view에서 boardnum, postnum, writer 가져오고, 댓글에서 대댓글 달기 클릭 시, 댓글의 num을 cmntnum으로 가져올 것.
    @PostMapping(value ="/post2/{cmntnum}")
    public CmntEntity create2(@PathVariable("cmntnum") int cmntnum, @RequestBody @Validated CmntDTO cmntDTO){
        CmntDTO cmnt2DTO = cmntDTO;
        cmnt2DTO.setDepth(1);
        cmnt2DTO.setCmntnum(cmntnum);
        return cmntService.saveCmnt(cmnt2DTO);
    }
    // QnA 게시판 답변 등록
    @PatchMapping("/answer")
    public void questionIsAnswered (int postnum){
        QuestionDTO questionDTO = questionService.getPost(postnum);
        questionDTO.setIsanswered(true);
        questionService.savePost(questionDTO);
    }
    @PostMapping(value ="/answer")
    public CmntEntity answer(@RequestBody @Validated CmntDTO cmntDTO){
        questionIsAnswered(cmntDTO.getPostnum());
        return cmntService.saveCmnt(cmntDTO);
    }
    // 댓글 수정
    @PatchMapping("/update/{num}")
    public String update(@RequestBody @Validated CmntDTO cmntDTO){
        cmntDTO.setContent(cmntDTO.getContent());
        cmntService.saveCmnt(cmntDTO);
        return "update ok!";
    }
    // 댓글 삭제(대댓글이 없을 경우)
    @DeleteMapping("/delete/{num}")
    public String deleteCmnt(@PathVariable("num") int num){
        cmntService.deleteCmnt(num);
        return "delete - deleteCmnt ok!";
    }

    // 댓글 삭제(대댓글이 있을 경우) - 값 변경
    @PutMapping("/delete/{num}")
    public String delete(@RequestBody @Validated CmntDTO cmntDTO){
        CmntDTO delCmntDTO = cmntDTO;
        boolean existCmnt2 = cmntService.existCmnt2(delCmntDTO.getNum());
        if(existCmnt2 == true) {
            delCmntDTO.setWriter("deleted");
            delCmntDTO.setContent("deleted");
            cmntService.saveCmnt(delCmntDTO);
            return "delete - changeCmnt ok!";
        } else {
            return deleteCmnt(delCmntDTO.getNum());
        }
    }
}
