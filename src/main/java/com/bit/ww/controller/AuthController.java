package com.bit.ww.controller;

import com.bit.ww.dto.LoginDTO;
import com.bit.ww.entity.MemberEntity;
import com.bit.ww.security.JwtTokenProvider;
import com.bit.ww.service.ImgService;
import com.bit.ww.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//로그인, 회원가입 Controller
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    JwtTokenProvider jwtTokenProvider;
    PasswordEncoder passwordEncoder;
    MemberService memberService;
    ImgService imgService;

        //회원가입
    @ApiOperation(value = "회원 등록", notes = "회원 등록")
    @PostMapping("/signup")
    public String signup(@RequestBody MemberEntity member) {
        // PW 암호화
        member.setPw(passwordEncoder.encode((member.getPw())));
        memberService.save(member);
        return "save ok";
    }

    //로그인
    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    public Optional<MemberEntity> login(@RequestBody LoginDTO loginDto) {
        // ID 회원조회
        Optional<MemberEntity> member = memberService.findbyId(loginDto.getId());
        // PW 확인
        if (!passwordEncoder.matches(loginDto.getPw(), member.get().getPw()))
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");

        // 토큰 생성
        member.get().setToken(jwtTokenProvider.createToken(member.get().getId(), member.get().getRole()));
        return member;
    }

    //회원 ID 조회
    @ApiOperation(value = "ID 중복확인", notes = "ID 중복확인")
    @PostMapping("/checkId")
    public boolean checkId(@RequestBody String id) {
        return memberService.checkId(id);
    }
}
