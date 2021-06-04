package com.bit.ww.controller;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.dto.FreeBoardDTO;
import com.bit.ww.entity.FreeBoardEntity;
import com.bit.ww.service.CmntService;
import com.bit.ww.service.FreeBoardService;
import com.bit.ww.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/freeBoard")
public class FreeBoardController {
    private FreeBoardService freeBoardService;
    private CmntService cmntService;
    private LikeService likeService;
    // Todo: 확인필요.
    // 게시물 전체 조회
    @GetMapping("/findAll")
    public List findAll(){
        return freeBoardService.getFreeBoardList();
    }
    // 게시물 전체 조회 개수
    @GetMapping("/cntFindAll")
    public int cntFindAll(){
        return freeBoardService.countList();
    }
    // Todo : 글 등록도 CmntyController에서 할 예정
    // 글 등록 - id 값은 필요 없음.
    @PostMapping("/post")
    public FreeBoardEntity write(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        return freeBoardService.savePost(freeBoardDTO);
    }

    // 글 수정
    @PatchMapping("/update/{num}")
    public String update(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        freeBoardDTO.setEditdate(LocalDateTime.now());
        freeBoardDTO.setTitle(freeBoardDTO.getTitle());
        freeBoardDTO.setContent(freeBoardDTO.getContent());
        freeBoardService.savePost(freeBoardDTO);
        return "update ok!";
    }

    // 글 상세보기 - Todo: Join으로 테이블을 합치기 !
    @GetMapping("/detail/{num}")
    public HashMap detail(@PathVariable("num") int num) {
        HashMap<String, Object> detail = new HashMap<>();
        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
        int readcount = freeBoardDTO.getReadcount()+1;
        freeBoardDTO.setReadcount(readcount);
        freeBoardService.savePost(freeBoardDTO);

        detail.put("freeBoardDTO", freeBoardDTO);
        detail.put("likecount", likeService.countLike(1, num));

        List<CmntDTO> fbCmntList = cmntService.getCmntList(1, num, 0);
        List<List<CmntDTO>> fbCmnt2List = new ArrayList<>();
        for(int i = 0; i<fbCmntList.size();i++){
            List<CmntDTO> temp = cmntService.getCmnt2List(1,num,fbCmntList.get(i).getNum(),1);
            fbCmnt2List.add(temp);
        }

        detail.put("cmntList", fbCmntList);
        detail.put("cmnt2List", fbCmnt2List);
        return detail;
    }
    // Todo : 수정 필요.
//    // 1. 조회수 증가
//    @GetMapping("/detail/{num}")
//    public FreeBoardDTO detail(@PathVariable("num") int num){
//        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
//        int readcount = freeBoardDTO.getReadcount()+1;
//        freeBoardDTO.setReadcount(readcount);
//        freeBoardService.savePost(freeBoardDTO);
//        return freeBoardDTO;
//    }
//    // 2. 좋아요 수
//    @GetMapping("/cntLike/{num}")
//    public int likecount(@PathVariable("num") int num){
//        //좋아요
//        return likeService.countLike(1, num);
//    }
//    //3. 좋아요 여부 Todo: 수정 필요.
//    @GetMapping("/isLiked/{num}")
//    public boolean isliked(@PathVariable("num") int num) {
//        // boolean isliked = likeService.existLikeColor(1,num,id);
//        return true;
//    }
//    //4. 댓글
//    @GetMapping("/cmntList/{num}")
//    public List cmntList(@PathVariable("num") int num) {
//        return cmntService.getCmntList(1, num, 0);
//    }
//    //5. 대댓글 Todo: 수정 필요.
//    @GetMapping("/cmnt2List/{num}")
//    public List cmnt2List(@PathVariable("num") int num) {
//        //대댓글 자체 > 댓글에 해당하는 대댓글 목록 > 해당 게시물의 댓글의 대댓글 목록
//        List<CmntDTO> fbCmntList = cmntService.getCmntList(1, num, 0);
//        List<List<CmntDTO>> fbCmnt2List = new ArrayList<>();
//        for(int i = 0; i<fbCmntList.size();i++){
//            List<CmntDTO> temp = cmntService.getCmnt2List(1,num,fbCmntList.get(i).getNum(),1);
//            fbCmnt2List.add(temp);
//        }
//        return fbCmnt2List;
//    }

    // 글 삭제
    @DeleteMapping("/delete/{num}")
    public String delete(@PathVariable("num") int num){
        freeBoardService.deletePost(num);
        return "delete ok!";
    }

    // 글 검색 - 제목
    @GetMapping("/searchByTitle")
    public List searchByTitleList(@RequestParam("search") String search){
        return freeBoardService.getListByTitle(search);
    }

    // 글 검색 - 제목 개수
    @GetMapping("/cntSearchByTitle")
    public int cntSearchByTitleList(@RequestParam("search") String search){
        return freeBoardService.countListByTitle(search);
    }
    // 글 검색 - 제목, 내용
    @GetMapping("/searchByTitleAndContent")
    public List searchByTitleAndContentList(@RequestParam("search") String search){
        return freeBoardService.getListByTitleAndContent(search, search);
    }
    // 글 검색 - 제목, 내용 개수
    @GetMapping("/cntSearchByTitleAndContent")
    public int cntSearchByTitleAndContentList(@RequestParam("search") String search){
        return freeBoardService.countListByTitleAndContent(search,search);
    }
    // 자유게시판 내 글보기
    @GetMapping("/myList/{writer}")
    public List myFBList(@PathVariable("writer") String writer){
        return freeBoardService.getListByWriter(writer);
    }
    // 자유게시판 내 글보기 개수
    @GetMapping("/cntMyList/{writer}")
    public int cntMyFBList(@PathVariable("writer") String writer){
        return freeBoardService.countListByWriter(writer);
    }
}
