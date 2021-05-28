package com.bit.ww.controller;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.dto.FreeBoardDTO;
import com.bit.ww.service.CmntService;
import com.bit.ww.service.FreeBoardService;
import com.bit.ww.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class FreeBoardController {
    private FreeBoardService freeBoardService;
    private CmntService cmntService;
    private LikeService likeService;

    // 조회 (완료)
    @GetMapping("/freeBoard")
    public List list(Model model){
        List<FreeBoardDTO> freeboardList = freeBoardService.getFreeBoardList();
        model.addAttribute("freeBoardList", freeboardList);
        int count = freeBoardService.countList();
        model.addAttribute("count", count);
        return freeboardList;
    }
    // Todo : 글 등록도 CmntyController에서 할 예정
    // 글 등록 (완료) - id 값은 필요 없음.
    @PostMapping("/freeBoard/post")
    public int write(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        return freeBoardService.savePost(freeBoardDTO);
    }

    // 글 수정 - 값 모두 필요. (완료)
    @PutMapping("/freeBoard/edit/{num}")
    public String update(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        freeBoardDTO.setEditdate(LocalDateTime.now());
        freeBoardService.savePost(freeBoardDTO);
        return "update ok!";
    }

    // 글 상세보기 - 해당하는 댓글 목록과 대댓글 목록도 함께 출력, 조회수 증가 (완료)
    @GetMapping("/freeBoard/detail/{num}")
    public String detail(@PathVariable("num") int num, Model model){
        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
        int readcount = freeBoardDTO.getReadcount()+1;
        freeBoardDTO.setReadcount(readcount);
        freeBoardService.savePost(freeBoardDTO);
        model.addAttribute("freeBoardDTO", freeBoardDTO);
        //좋아요
        int likecount = likeService.countLike(1, num);
        model.addAttribute("likecount",likecount);
        // Todo: Login된 아이디 필요.
        // boolean isliked = likeService.existLikeColor(1,num,id);
        //댓글 Todo: fbCmntList.get(i).getNum() 이 부분 확인 필수.
        List<CmntDTO> fbCmntList = cmntService.getCmntList(1,num,0);
        model.addAttribute("fbCmntList", fbCmntList);
        //대댓글
        //대댓글 자체 > 댓글에 해당하는 대댓글 목록 > 해당 게시물의 댓글의 대댓글 목록
        List<List<CmntDTO>> fbCmnt2List = new ArrayList<>();
        for(int i = 0; i<fbCmntList.size();i++){
            List<CmntDTO> temp = cmntService.getCmnt2List(1,num,fbCmntList.get(i).getNum(),1);
            fbCmnt2List.add(temp);
        }
        model.addAttribute("fbCmnt2List", fbCmnt2List);
        return "detail ok!";
    }

    // 글 삭제 (완료)
    @DeleteMapping("/freeBoard/delete/{num}")
    public String delete(@PathVariable("num") int num){
        freeBoardService.deletePost(num);
        return "delete ok!";
    }

    // 글 검색 - 제목 (완료)
    @GetMapping("/freeBoard/searchByTitle")
    public List searchByTitleList(@RequestParam("search") String search, Model model){
        List<FreeBoardDTO> searchList = freeBoardService.getListByTitle(search);
        model.addAttribute("searchList", searchList);
        int countListByTitle = freeBoardService.countListByTitle(search);
        model.addAttribute("countListByTitle", countListByTitle);
        return searchList;
    }
    // 글 검색 - 제목, 내용 (완료)
    @GetMapping("/freeBoard/searchByTitleAndContent")
    public List searchByTitleAndContentList(@RequestParam("search") String search, Model model){
        List<FreeBoardDTO> searchList = freeBoardService.getListByTitleAndContent(search, search);
        model.addAttribute("searchList", searchList);
        int countListByTitleAndContent = freeBoardService.countListByTitleAndContent(search,search);
        model.addAttribute("countListByTitleAndContent", countListByTitleAndContent);
        return searchList;
    }

    // 자유게시판 내 글보기
    // Todo: 내가 쓴 글 보기는 동일한 메소드에 HttpServletRequest를 통해 nickname 가져오기
    @GetMapping("/freeBoard/myList")
    public List myFBList(@RequestParam("writer") String writer, Model model){
        List<FreeBoardDTO> myFbList = freeBoardService.getListByWriter(writer);
        model.addAttribute("myFbList", myFbList);
        int countMyFbList = freeBoardService.countListByWriter(writer);
        model.addAttribute("countMyFbList", countMyFbList);
        return myFbList;
    }
}
