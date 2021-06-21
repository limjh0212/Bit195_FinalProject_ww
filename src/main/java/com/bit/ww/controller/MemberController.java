package com.bit.ww.controller;

import com.bit.ww.entity.MemberEntity;
import com.bit.ww.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Secured({"ROLE_USER"})
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    //회원 전체 조회
    @ApiOperation(value = "회원 조회", notes = "회원 조회")
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/findall")
    public List<MemberEntity> findAll() {
        return memberService.findAll();
    }

    //회원 ID 조회
    @ApiOperation(value = "회원 ID조회", notes = "회원 ID조회")
    @GetMapping("/findbyid/{id}")
    public Optional<MemberEntity> findbyid(@PathVariable String id) {
        return memberService.findbyId(id);
    }

    //회원 이미지 조회
    //imgController에 존재

    //회원 삭제
    @ApiOperation(value = "회원 삭제", notes = "회원 삭제")
    @DeleteMapping("/delete/{id}")
    @Secured({"ROLE_ADMIN"})
    public String delete(@PathVariable String id) {
        memberService.deleteById(id);
        return "delete ok!";
    }

//    //회원 수정
//    @ApiOperation(value = "회원 수정", notes = "회원 수정")
//    @PatchMapping("/update")
//    public String update(@RequestBody MemberEntity member) {
//        member.setPw(passwordEncoder.encode((member.getPw()))); // PW 암호화
//        memberService.save(member);
//        return "update ok!";
//    }
    //회원 수정
    @ApiOperation(value = "회원 수정", notes = "회원 수정")
    @PatchMapping("/update")
    public String update(@RequestBody MemberEntity member) {
        member.setPw(passwordEncoder.encode((member.getPw()))); // PW 암호화
        memberService.save(member);
        return "update ok!";
    }
    //회원 탈퇴
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴")
    @PatchMapping("/wthdr")
    public String wthdr(@RequestBody MemberEntity member) {
        member.setIswithdrawal(true);
        member.setWthdrdate(LocalDateTime.now());
        memberService.save(member);
        return "wthdr ok!";
    }
}



