package com.bit.ww.controller;

import com.bit.ww.service.BoardService;
import com.bit.ww.service.CmntService;
import com.bit.ww.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/mngmt")
public class MngmtController {
    private final BoardService boardService;
    private final MemberService memberService;
    private final CmntService cmntService;

    // 관리자 페이지
    @ApiOperation(value = "관리자 메인 페이지", notes = "총 회원수, 성별 연령별 회원 수, 총 게시물 수")
    @GetMapping("/admin")
    public HashMap infos(){
        HashMap<String, Object> infos = new HashMap<>();
        infos.put("cntMembers", memberService.cntMembers());
        infos.put("cntM10", memberService.cntGenderAndAge(true, 10, 19));
        infos.put("cntW10", memberService.cntGenderAndAge(false, 10, 19));
        infos.put("cntM20", memberService.cntGenderAndAge(true, 20, 29));
        infos.put("cntW20", memberService.cntGenderAndAge(false, 20, 29));
        infos.put("cntM30", memberService.cntGenderAndAge(true, 30, 39));
        infos.put("cntW30", memberService.cntGenderAndAge(false, 30, 39));
        infos.put("cntM40", memberService.cntGenderAndAge(true, 40, 49));
        infos.put("cntW40", memberService.cntGenderAndAge(false, 40, 49));
        infos.put("cntTotalPosts", boardService.cntTotalPosts(5));
        // 코디 데이터 정보
        return infos;
    }
    @ApiOperation(value = "멤버관리 페이지", notes = "총 회원수, 가입회원 수, 탈퇴 회원수, 일주일간 각 날짜별 가입 및 탈퇴 회원수")
    @GetMapping("/member")
    public HashMap memberInfos(){
        HashMap<String, Object> infos = new HashMap<>();
        infos.put("cntMembers", memberService.cntMembers());
        infos.put("cntWthdrFalse", memberService.cntWthdrFalse());
        infos.put("cntWthdrTrue", memberService.cntWthdrTrue());
        //infos.put("cnt7days", memberService.cntToday());
        return infos;
    }
    // 회원 목록은 MemberController에 있음.
    @ApiOperation(value = "커뮤니티 관리 페이지", notes = "총 게시물수, 각 게시판 별 게시물수, 최근게시물 수, 최근 각 게시판별 게시물 수")
    @GetMapping("/cmnty")
    public HashMap cmntyInfos(){
        HashMap<String, Object> infos = new HashMap<>();
        infos.put("cntTotalPosts", boardService.cntTotalPosts(5));
        infos.put("cntFreeBoard", boardService.cntPosts("freeBoard"));
        infos.put("cntOOTD", boardService.cntPosts("OOTD"));
        infos.put("cntHotDeal", boardService.cntPosts("hotDeal"));
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.now();
        infos.put("cntRecentPosts", boardService.cntRecentPosts(start, end,5));
        infos.put("cntRecentFB",boardService.cntRecentBoard(start, end,1));
        infos.put("cntRecentOOTD", boardService.cntRecentBoard(start, end,2));
        infos.put("cntRecentHD", boardService.cntRecentBoard(start, end,3));
        return infos;
    }
    // 게시글 목록은 BoardController에 있음.
    @ApiOperation(value = "문의 관리 페이지", notes = "최근 문의 수, 미해결문의 수, 미해결문의리스트")
    @GetMapping("/qna")
    public HashMap qnaInfos(){
        HashMap<String, Object> infos = new HashMap<>();
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.now();
        infos.put("cntRecentQuestion", boardService.cntRecentBoard(start, end,4));
        infos.put("cntNotAnswered",boardService.cntNotAnswered());
        infos.put("NotAnsweredQuestion", boardService.findNotAnswered());
        // 작성자 검색
        return infos;
    }
    // 마이 페이지
    // 나머지는 내글보기와 동일
    @ApiOperation(value = "마이 페이지 - 내가 남긴 댓글", notes = "내가 남긴 댓글")
    @GetMapping("/mypage/mycmnts")
    public List mycmnts(String writer){
        return cmntService.getMyCmntList(writer);
    }
}
