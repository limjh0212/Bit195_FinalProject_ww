package com.bit.ww.controller;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.dto.FreeBoardDTO;
import com.bit.ww.dto.LikeDTO;
import com.bit.ww.dto.TempDTO;
import com.bit.ww.service.CmntService;
import com.bit.ww.service.FreeBoardService;
import com.bit.ww.service.LikeService;
import com.bit.ww.service.TempService;
import lombok.AllArgsConstructor;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CmntyController {
    private FreeBoardService freeBoardService;
    // OOTD
    // 핫딜
    // 문의사항
    private CmntService cmntService;
    private TempService tempService;
    private LikeService likeService;

    // 인기글 조회
    // 글 등록 (ajax로 화면 전환)
    // 글 임시저장 등록
    @PostMapping("/community/tempPost")
    public String tempWrite(@RequestParam("boardnum") int boardnum, @RequestBody @Validated TempDTO tempDTO){
        TempDTO fbTemp = tempDTO;
        fbTemp.setBoardnum(boardnum);
        tempService.saveTemp(fbTemp);
        return "tempPost ok!";
    }
    //글 임시저장 값 가져오기
    @GetMapping("/community/post")
    public String getTemp(@RequestParam("tempnum") int tempnum, Model model){
        TempDTO tempDTO = tempService.getTemp(tempnum);
        model.addAttribute("tempDTO", tempDTO);
        return "getTemp ok!";
    }
    // 글 임시저장한 목록 가져오기
    //  Todo: 로그인세션으로 작성자 id 가져오기 (writer=nickname) 추가 필요.
    @GetMapping("/community/tempList/{writer}")
    public List tempList(@PathVariable("writer") String writer, Model model){
        List<TempDTO> tempList = tempService.getTempList(writer);
        model.addAttribute("tempList", tempList);
        int tempCount = tempService.countTempList(writer);
        model.addAttribute("tempCount", tempCount);
        return tempList;
    }

    // 글 임시저장 삭제
    // Todo: 작성일로부터 일정 시간이 지나면 삭제되는 쿼리 작성 필요.
    @DeleteMapping("/community/tempList/delete/{tempnum}")
    public String tempDelete(@PathVariable("tempnum") int tempnum){
        tempService.deleteTemp(tempnum);
        return "tempDelete ok!";
    }

    // 좋아요 조회
    @GetMapping("/{boardname}/like/{num}") // "/{boardname}/detail/{num}"
    public String existlike(@PathVariable("boardname") String boardname, @PathVariable("num") int num, Model model){
        int boardnum = 0;
        if (boardname=="freeBoard"){
            boardnum = 1;
        }
        int existLike = 0;
        // Todo: userid는 로그인 세션으로 가져올 것. 우선 임의로 배정
        if (likeService.existLike(boardnum,num,"ww") ==true){
            existLike =1;
        }
        model.addAttribute("existLike", existLike);
        int countLike = likeService.countLike(boardnum,num);
        model.addAttribute("countLike", countLike);
        return "like ok!";

    }
    // 좋아요 버튼 클릭 // Todo: ajax로 실시간 반영 - 수정 필요
    @PostMapping("/clickLikeBtn")
    public String checkLike(@RequestBody @Validated LikeDTO likeDTO){
        boolean existLike = likeService.existLike(likeDTO.getBoardnum(), likeDTO.getPostnum(), "ww");
        if (existLike==true){
            deleteLike(likeDTO.getBoardnum(),likeDTO.getPostnum(),"ww");
        }else{
            createLike(likeDTO);
        }
        return "checkLike ok!";
    }
    // 좋아요 생성
    @PostMapping("/createLike")
    public int createLike(@RequestBody @Validated LikeDTO likeDTO){
        return likeService.saveLike(likeDTO);
    }
    // 좋아요 삭제 Todo: 굳이 두번 쿼리문 실행하지 말고, delete 쿼리문을 만들 것!
    @PostMapping("/deleteLike")
    public String deleteLike(@RequestParam("boardnum") int boardnum, @RequestParam("postnum")int postnum, @RequestParam("userid") String userid){
        int num = likeService.findLikeNum(boardnum, postnum, "ww");
        likeService.deleteLike(num);
        return "deleteLike ok!";
    }
    // 마이페이지, 게시판 내글보기 - 내가 쓴 글 보기

    @GetMapping("/mypage/myPost")
    public String myList(@RequestParam("writer") String writer, Model model){
        // 자유게시판
        List<FreeBoardDTO> myFBList = freeBoardService.getListByWriter(writer);
        model.addAttribute("myFBList", myFBList);
        int countMyFbList = freeBoardService.countListByWriter(writer);
        model.addAttribute("countMyFbList", countMyFbList);
        // OOTD
        // 핫딜
        // 문의사항
        //댓글 Todo: boardnum을 boardname으로 가져와야 함. 클릭 시 상세보기로 이동.
        List<CmntDTO> myCmntList = cmntService.getMyCmntList(writer);
        return "mypage ok!";
    }
    @GetMapping("/mypage/myPost/searchByTitle")
    public List searchMyPostByTitle(@RequestParam("title") String title, @RequestParam("writer") String writer, Model model){
        // 자유게시판
        List<FreeBoardDTO> fbSearchList = freeBoardService.getListByWriterAndTitle(title, writer);
        model.addAttribute("fbSearchList", fbSearchList);
        int countMyFbSearchList = freeBoardService.countListByWriterAndTitle(title, writer);
        model.addAttribute("countMyFbSearchList", countMyFbSearchList);
        // OOTD
        // 핫딜
        // 문의사항
        return fbSearchList;
    }
    @GetMapping("/mypage/myPost/searchByTitleAndContent")
    public List searchMyPostByTitleAndContent(@RequestParam("search") String search, @RequestParam("nickname") String nickname, Model model){
        // 자유게시판
        List<FreeBoardDTO> fbSearchList = freeBoardService.getListByWriterAndTitleAndContent(search, search, nickname);
        model.addAttribute("fbSearchList", fbSearchList);
        int countMyFbSearchList = freeBoardService.countListByWriterAndTitleAndContent(search, search, nickname);
        model.addAttribute("countMyFbSearchList", countMyFbSearchList);
        // OOTD
        // 핫딜
        // 문의사항
        return fbSearchList;
    }

    // 관리자  Todo: 생성 필요 - 대시보드 형태

    // 글 검색 - 작성자 검색
    @GetMapping("/admin/searchByWriter")
    public List searchByWriter(@RequestParam("writer") String writer, Model model){
        List<FreeBoardDTO> writerSearchList = freeBoardService.getListByWriter(writer);
        model.addAttribute("writerSearchList", writerSearchList);
        int countWriterSearchList = freeBoardService.countListByWriter(writer);
        model.addAttribute("countWriterSearchList", countWriterSearchList);
        return writerSearchList;
    }


}
