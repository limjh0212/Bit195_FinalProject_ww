package com.bit.ww.controller;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.dto.FreeBoardDTO;
import com.bit.ww.dto.LikeDTO;
import com.bit.ww.dto.TempDTO;
import com.bit.ww.entity.TempEntity;
import com.bit.ww.service.*;
import lombok.AllArgsConstructor;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cmnty")
public class CmntyController {
    private FreeBoardService freeBoardService;
    // OOTD
    // 핫딜
    private QuestionService questionService;
    private CmntService cmntService;
    private TempService tempService;
    private LikeService likeService;

    // 인기글 조회
    // 글 등록 (ajax로 화면 전환)
    // 글 임시저장 등록
    @PostMapping("/tempPost")
    public TempEntity tempWrite(@RequestParam("boardnum") int boardnum, @RequestBody @Validated TempDTO tempDTO){
        TempDTO fbTemp = tempDTO;
        fbTemp.setBoardnum(boardnum);
        return tempService.saveTemp(fbTemp);
    }
    //글 임시저장 값 가져오기
    @GetMapping("/getTempPost")
    public TempDTO getTemp(@RequestParam("tempnum") int tempnum, Model model){
        return tempService.getTemp(tempnum);
    }
    // 글 임시저장한 목록 가져오기
    //  Todo: 로그인세션으로 작성자 id 가져오기 (writer=nickname) 추가 필요.
    @GetMapping("/tempList/{writer}")
    public List tempList(@PathVariable("writer") String writer){
        return tempService.getTempList(writer);
    }
    // 글 임시저장한 목록 개수 가져오기
    @GetMapping("/cntTempList/{writer}")
    public int cntTempList(@PathVariable("writer") String writer){
        return tempService.countTempList(writer);
    }
    // 글 임시저장 삭제
    // Todo: 작성일로부터 일정 시간이 지나면 삭제되는 쿼리 작성 필요.
    @DeleteMapping("/tempList/delete/{tempnum}")
    public String tempDelete(@PathVariable("tempnum") int tempnum){
        tempService.deleteTemp(tempnum);
        return "tempDelete ok!";
    }

    // 좋아요 조회 Todo: 수정 필요.
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
    @GetMapping("/mypage/myPost/myList")
    public HashMap myList(@RequestParam("writer") String writer){
        HashMap<String,List> myList = new HashMap<String, List>();
        myList.put("myFBList", freeBoardService.getListByWriter(writer));
        //myList.put("myOOTDList", );
        //myList.put("myHotDealList", );
        myList.put("myQnAList", questionService.getListByWriter(writer));
        // Todo: boardnum을 boardname으로 가져와야 함. 클릭 시 상세보기로 이동.
        myList.put("myCmntList", cmntService.getMyCmntList(writer));
        return myList;
    }

    // 마이페이지, 게시판 내글보기 - 내가 쓴 글 개수 보기
    @GetMapping("/mypage/myPost/cntMyList")
    public HashMap cntMyList(@RequestParam("writer") String writer){
        HashMap<String,Integer> cntMyList = new HashMap<String, Integer>();
        cntMyList.put("cntMyFBList", freeBoardService.countListByWriter(writer));
        // OOTD
        // 핫딜
        cntMyList.put("cntMyQnAList", questionService.countListByWriter(writer));
        return cntMyList;
    }
    @GetMapping("/mypage/myPost/searchByTitle")  // = 자유게시판 버전만 존재
    public HashMap searchMyPostByTitle(@RequestParam("search") String search, @RequestParam("writer") String writer){
        HashMap<String,List> searchMyPostByTitleList = new HashMap<String, List>();
        searchMyPostByTitleList.put("fBResult", freeBoardService.getListByWriterAndTitle(search, writer));
        // OOTD
        // 핫딜
        return searchMyPostByTitleList;
    }
    @GetMapping("/mypage/myPost/cntSearchByTitle") // = 자유게시판 버전만 존재
    public HashMap cntSearchMyPostByTitle(@RequestParam("search") String search, @RequestParam("writer") String writer){
        HashMap<String,Integer> cntSearchMyPostByTitleList = new HashMap<String, Integer>();
        cntSearchMyPostByTitleList.put("cntFBResult", freeBoardService.countListByWriterAndTitle(search, writer));
        // OOTD
        // 핫딜
        return cntSearchMyPostByTitleList;
    }
    // Todo : HashMap 형태로 변경필요.
    @GetMapping("/mypage/myPost/searchByTitleAndContent") // = 자유게시판 버전만 존재
    public List searchMyPostByTitleAndContent(@RequestParam("search") String search, @RequestParam("writer") String writer){
        // 자유게시판
        // OOTD
        // 핫딜
        // 문의사항
        return freeBoardService.getListByWriterAndTitleAndContent(search, search, writer);
    }
    // Todo : HashMap 형태로 변경필요.
    @GetMapping("/mypage/myPost/cntSearchByTitleAndContent")
    public int cntSearchMyPostByTitleAndContent(@RequestParam("search") String search, @RequestParam("writer") String writer){
        // 자유게시판
        // OOTD
        // 핫딜
        // 문의사항
        return freeBoardService.countListByWriterAndTitleAndContent(search, search, writer);
    }
    // 관리자  Todo: 생성 필요 - 대시보드 형태

    // 글 검색 - 작성자 검색 // = 자유게시판 버전만 존재
    @GetMapping("/admin/searchByWriter")
    public HashMap searchByWriter(@RequestParam("writer") String writer){
        HashMap<String,List> writerSearchList = new HashMap<String, List>();
        writerSearchList.put("fBResult",freeBoardService.getListByWriter(writer));
        //OOTD
        //핫딜
        writerSearchList.put("QnAResult",questionService.getListByWriter(writer));
        return writerSearchList;
    }
    // 글 검색 - 작성자 검색 개수 // = 자유게시판 버전만 존재
    @GetMapping("/admin/cntSearchByWriter")
    public HashMap cntSearchByWriter(@RequestParam("writer") String writer){
        HashMap<String,Integer> cntWriterSearchList = new HashMap<String, Integer>();
        cntWriterSearchList.put("cntFBResult", freeBoardService.countListByWriter(writer));
        //OOTD
        //핫딜
        cntWriterSearchList.put("cntQnAResult", questionService.countListByWriter(writer));
        return cntWriterSearchList;
    }

}
