package com.bit.ww.controller;

import com.bit.ww.dto.CmntDTO;
import com.bit.ww.dto.FreeBoardDTO;
import com.bit.ww.dto.TempDTO;
import com.bit.ww.service.CmntService;
import com.bit.ww.service.FreeBoardService;
import com.bit.ww.service.TempService;
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
    private TempService tempService;

    // 조회 (완료)
    @GetMapping("/freeBoard")
    public List list(Model model){
        List<FreeBoardDTO> freeboardList = freeBoardService.getFreeBoardList();
        model.addAttribute("freeBoardList", freeboardList);
        int count = freeBoardService.countList();
        model.addAttribute("count", count);
        return freeboardList;
    }

    // 글 등록 (완료) - id 값은 필요 없음.
    @PostMapping("/freeBoard/post")
    public int write(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        return freeBoardService.savePost(freeBoardDTO);
    }
    // 글 임시저장 등록 Todo: 확인필요.
    @PostMapping("/freeBoard/post/temp")
    public String tempWrite(@RequestBody @Validated TempDTO tempDTO){
        TempDTO fbTemp = tempDTO;
        fbTemp.setBoardnum(1);
        tempService.saveTemp(fbTemp);
        return "tempPost ok!";
    }

    // 글 임시저장 가져오기 Todo: 확인필요.
    @GetMapping("/freeBoard/post")
    public String getTemp(@RequestParam("tempnum") int tempnum, Model model){
        TempDTO tempDTO = tempService.getTemp(tempnum);
        model.addAttribute("tempDTO", tempDTO);
        return "getTemp ok!";
    }

    // 글 임시저장한 목록 가져오기 Todo: 확인 필요
    //  Todo: 로그인세션으로 작성자 id 가져오기 (writer=nickname) 추가 필요.
    @GetMapping("/boards/post/tempList/{nickname}")
    public List tempList(@PathVariable("nickname") String nickname, Model model){
        List<TempDTO> tempList = tempService.getTempList(nickname);
        model.addAttribute("tempList", tempList);
        int tempCount = tempService.countTempList(nickname);
        model.addAttribute("tempCount", tempCount);
        return tempList;
    }

    // 글 임시저장 삭제 Todo: 확인 필요.
    // Todo: 작성일로부터 일정 시간이 지나면 삭제되는 쿼리 작성 필요.
    @DeleteMapping("/boards/tempDelete/{tempnum}")
    public String tempDelete(@PathVariable("tempnum") int tempnum){
        tempService.deleteTemp(tempnum);
        return "tempDelete ok!";
    }

    // 글 수정 - 값 모두 필요. Todo: 확인 필요
    @PutMapping("/freeBoard/edit/{num}")
    public String update(@RequestBody @Validated FreeBoardDTO freeBoardDTO){
        LocalDateTime now = LocalDateTime.now();
        freeBoardDTO.setUpdatedate(now);
        freeBoardService.savePost(freeBoardDTO);
        return "update ok!";
    }

    // 글 상세보기 - 해당하는 댓글 목록과 대댓글 목록도 함께 출력, 조회수 증가 Todo: 확인 필요
    @GetMapping("/freeBoard/detail/{num}")
    public String detail(@PathVariable("num") int num, Model model){
        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
        int readcount = freeBoardDTO.getReadcount()+1;
        freeBoardDTO.setReadcount(readcount);
        freeBoardService.savePost(freeBoardDTO);
        model.addAttribute("freeBoardDTO", freeBoardDTO);
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
    // 관리 기능
    // 마이페이지, 게시판 내글보기 - 내가 쓴 글 보기
    // Todo: 내가 쓴 글 보기는 동일한 메소드에 HttpServletRequest를 통해 nickname 가져오기
    @GetMapping("/freeBoard/myList")
    public List myFBList(@RequestParam("nickname") String nickname, Model model){
        List<FreeBoardDTO> myFbList = freeBoardService.getListByWriter(nickname);
        model.addAttribute("myFbList", myFbList);
        int countMyFbList = freeBoardService.countListByWriter(nickname);
        model.addAttribute("countMyFbList", countMyFbList);
        return myFbList;
    }
    @GetMapping("/mypage/myPost")
    public String myList(@RequestParam("nickname") String nickname, Model model){
        List<FreeBoardDTO> myFBList = freeBoardService.getListByWriter(nickname);
        model.addAttribute("myFBList", myFBList);
        int countMyFbList = freeBoardService.countListByWriter(nickname);
        model.addAttribute("countMyFbList", countMyFbList);
        //댓글 Todo: 확인 필요
        List<CmntDTO> myCmntList = cmntService.getMyCmntList(nickname);
        return "mypage ok!";
    }
    @GetMapping("/mypage/myPost/searchByTitle")
    public List searchMyPostByTitle(@RequestParam("search") String search, @RequestParam("nickname") String nickname, Model model){
        List<FreeBoardDTO> searchList = freeBoardService.getListByWriterAndTitle(search, nickname);
        model.addAttribute("searchList", searchList);
        int countMySearchList = freeBoardService.countListByWriterAndTitle(search, nickname);
        model.addAttribute("countMySearchList", countMySearchList);
        return searchList;
    }
    @GetMapping("/mypage/myPost/searchByTitleAndContent")
    public List searchMyPostByTitleAndContent(@RequestParam("search") String search, @RequestParam("nickname") String nickname, Model model){
        List<FreeBoardDTO> searchList = freeBoardService.getListByWriterAndTitleAndContent(search, search, nickname);
        model.addAttribute("searchList", searchList);
        int countMySearchList = freeBoardService.countListByWriterAndTitleAndContent(search, search, nickname);
        model.addAttribute("countMySearchList", countMySearchList);
        return searchList;
    }
    // 관리자 - 글 검색 - 작성자 Todo: 확인 필요
    @GetMapping("/main/admin/searchBy{writer}")
    public List searchByWriter(@PathVariable("writer") String writer, Model model){
        List<FreeBoardDTO> searchList = freeBoardService.getListByWriter(writer);
        model.addAttribute("searchList", searchList);
        int countListByWriter = freeBoardService.countListByWriter(writer);
        model.addAttribute("countListByWriter", countListByWriter);
        return searchList;
    }



}
